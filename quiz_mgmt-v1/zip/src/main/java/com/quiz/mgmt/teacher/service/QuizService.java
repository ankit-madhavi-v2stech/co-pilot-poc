package com.quiz.mgmt.teacher.service;

import com.quiz.mgmt.entity.*;
import com.quiz.mgmt.model.TestSubmissionModel;
import com.quiz.mgmt.repository.*;
import com.quiz.mgmt.student.model.QuestionStatusModel;
import com.quiz.mgmt.student.model.Report;
import com.quiz.mgmt.student.model.ScoreCardModel;
import com.quiz.mgmt.teacher.model.AnswerModel;
import com.quiz.mgmt.teacher.model.QuestionModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class QuizService {

    @Autowired
    private QuizQuestionRepository quizQuestionRepository;

    @Autowired
    private AnswerRepository answerRepository;

    @Autowired
    private SubjectRepository subjectRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ScoreCardRepository scoreCardRepository;

    public void saveQuestionsAndAnswers(List<QuestionModel> questionList , String subjectName) {
        Subject subject = subjectRepository.findBySubjectName(subjectName)
                .orElseThrow(() -> new RuntimeException("Subject not found: " + subjectName));

        for (QuestionModel questionModel : questionList) {
            QuizQuestion quizQuestion = new QuizQuestion();
            quizQuestion.setQuestion(questionModel.getQuestion());
            quizQuestion.setSubject(subject);
            quizQuestion.setCreatedAt(new Date());
            quizQuestion.setUpdatedAt(new Date());

            quizQuestion = quizQuestionRepository.save(quizQuestion);

            for (AnswerModel answerModel : questionModel.getAnswers()) {
                Answer answer = new Answer();
                answer.setAnswer(answerModel.getAnswer());
                answer.setCorrect(answerModel.getCorrectAnswer() == 'Y' || answerModel.getCorrectAnswer() == 'y');
                answer.setQuestion(quizQuestion);
                answer.setCreatedAt(new Date());
                answer.setUpdatedAt(new Date());
                answerRepository.save(answer);
            }
        }
    }

    public List<QuestionModel> getAllQuestionsWithAnswers(String subjectName) {
        Subject subject = subjectRepository.findBySubjectName(subjectName)
                .orElseThrow(() -> new RuntimeException("Subject not found: " + subjectName));

        List<QuizQuestion> quizQuestions = quizQuestionRepository.findBySubject(subject);

        List<QuestionModel> questionModels = new ArrayList<>();
        for (QuizQuestion quizQuestion : quizQuestions) {
            List<Answer> answers = answerRepository.findByQuestion(quizQuestion);

            List<AnswerModel> answerModels = answers.stream()
                    .map(answer -> new AnswerModel(answer.getAnswer(), answer.isCorrect() ? 'Y' : 'N'))
                    .collect(Collectors.toList());

            QuestionModel questionModel = new QuestionModel(quizQuestion.getQuestion(), answerModels);
            questionModels.add(questionModel);
        }

        return questionModels;
    }

    public QuestionModel createQuestion(String subjectName, QuestionModel questionModel) {
        Subject subject = subjectRepository.findBySubjectName(subjectName)
                .orElseThrow(() -> new RuntimeException("Subject not found: " + subjectName));

        final QuizQuestion quizQuestion = new QuizQuestion();
        quizQuestion.setQuestion(questionModel.getQuestion());
        quizQuestion.setSubject(subject);
        quizQuestion.setCreatedAt(new Date());

        List<Answer> answers = questionModel.getAnswers().stream()
                .map(answerModel -> {
                    Answer answer = new Answer();
                    answer.setAnswer(answerModel.getAnswer());
                    answer.setCorrect(answerModel.getCorrectAnswer() == 'Y');
                    answer.setQuestion(quizQuestion);
                    answer.setCreatedAt(new Date());
                    return answer;
                })
                .collect(Collectors.toList());

        QuizQuestion savedQuizQuestion = quizQuestionRepository.save(quizQuestion);

        return new QuestionModel(savedQuizQuestion.getQuestion(), answers.stream()
                .map(answer -> new AnswerModel(answer.getAnswer(), answer.isCorrect() ? 'Y' : 'N'))
                .collect(Collectors.toList()));
    }

    public QuestionModel updateQuestion(String subjectName, Long questionId, QuestionModel questionModel) {
        Subject subject = subjectRepository.findBySubjectName(subjectName)
                .orElseThrow(() -> new RuntimeException("Subject not found: " + subjectName));

        final QuizQuestion quizQuestion = quizQuestionRepository.findByIdAndSubject(questionId.intValue(), subject)
                .orElseThrow(() -> new RuntimeException("Question not found: " + questionId));

        quizQuestion.setQuestion(questionModel.getQuestion());

        // Delete old answers
        List<Answer> oldAnswers = answerRepository.findByQuestion(quizQuestion);
        answerRepository.deleteAll(oldAnswers);

        // Add new answers
        List<Answer> answers = questionModel.getAnswers().stream()
                .map(answerModel -> {
                    Answer answer = new Answer();
                    answer.setAnswer(answerModel.getAnswer());
                    answer.setCorrect(answerModel.getCorrectAnswer() == 'Y');
                    answer.setQuestion(quizQuestion);
                    return answer;
                })
                .collect(Collectors.toList());

        QuizQuestion savedQuizQuestion = quizQuestionRepository.save(quizQuestion);

        return new QuestionModel(savedQuizQuestion.getQuestion(), answers.stream()
                .map(answer -> new AnswerModel(answer.getAnswer(), answer.isCorrect() ? 'Y' : 'N'))
                .collect(Collectors.toList()));
    }

    public void deleteQuestion(String subjectName, Long questionId) {
        Subject subject = subjectRepository.findBySubjectName(subjectName)
                .orElseThrow(() -> new RuntimeException("Subject not found: " + subjectName));

        QuizQuestion quizQuestion = quizQuestionRepository.findByIdAndSubject(questionId.intValue(), subject)
                .orElseThrow(() -> new RuntimeException("Question not found: " + questionId));

        quizQuestionRepository.delete(quizQuestion);
    }

    public List<QuestionModel> getRandomQuestions(String subjectName) {
        Pageable pageable = PageRequest.of(0, 5);
        List<QuizQuestion> quizQuestions = quizQuestionRepository.findRandomQuestions(subjectName, pageable);
        List<QuestionModel> questionModels = new ArrayList<>();
        for (QuizQuestion quizQuestion : quizQuestions) {
            List<Answer> answers = answerRepository.findByQuestion(quizQuestion);

            List<AnswerModel> answerModels = answers.stream()
                    .map(answer -> new AnswerModel(answer.getAnswer(), null)) // set correctAnswer as null
                    .collect(Collectors.toList());

            QuestionModel questionModel = new QuestionModel(quizQuestion.getId(),quizQuestion.getQuestion(), answerModels);
            questionModels.add(questionModel);
        }

        return questionModels;
    }

    public Report getCorrectAnswers(TestSubmissionModel testSubmissionModel) {
        List<QuestionStatusModel> correctAnswers = new ArrayList<>();
        List<QuestionModel> questions = testSubmissionModel.getQuestions();
        Integer score = 0;
        Integer totalQuestions = questions.size();
        Subject subject = null;
        for (QuestionModel questionModel : questions) {
            QuizQuestion quizQuestion = quizQuestionRepository.findById(questionModel.getQuestionId())
                    .orElseThrow(() -> new RuntimeException("Question not found: " + questionModel.getQuestionId()));
            subject = quizQuestion.getSubject();
            Answer correctAnswer = answerRepository.findByQuestionAndCorrect(quizQuestion, true)
                    .orElseThrow(() -> new RuntimeException("Correct answer not found for question: " + questionModel.getQuestionId()));
            Optional<AnswerModel> submittedAnswer = questionModel.getAnswers().stream()
                    .filter(answerModel -> answerModel.getCorrectAnswer() != null && answerModel.getCorrectAnswer() == 'Y')
                    .findFirst();
            if (submittedAnswer.isPresent() && submittedAnswer.get().getAnswer().equals(correctAnswer.getAnswer())) {
                score++;
            }
            QuestionStatusModel questionStatusModel = new QuestionStatusModel(quizQuestion.getId(), quizQuestion.getQuestion(),correctAnswer.getAnswer(), submittedAnswer.get().getAnswer(), submittedAnswer.get().getAnswer().equals(correctAnswer.getAnswer()));
            correctAnswers.add(questionStatusModel);
        }
        ScoreCard scoreCard = new ScoreCard();
        scoreCard.setStudent(getUserById(testSubmissionModel.getUserId()));
        scoreCard.setSubject(subject);
        scoreCard.setScore(score);
        scoreCard.setMaxScore(totalQuestions);
        scoreCard.setCreatedAt(new Date());
        scoreCardRepository.save(scoreCard);
        return new Report(correctAnswers, score);
    }

    public User getUserById(Integer userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found: " + userId));
    }

    public List<ScoreCardModel> fetchResultsForUser(Integer userId) {
        List<ScoreCard> scoreCards = scoreCardRepository.findByStudent(getUserById(userId));

        return scoreCards.stream()
                .map(scoreCard -> {
                    ScoreCardModel model = new ScoreCardModel();
                    model.setSubject(scoreCard.getSubject().getSubjectName());
                    model.setMarksObtained(scoreCard.getScore());
                    model.setMaxMarks(scoreCard.getMaxScore());
                    model.setCreatedAt(scoreCard.getCreatedAt());
                    return model;
                })
                .collect(Collectors.toList());
    }

    public List<ScoreCardModel> fetchResults(int pageNumber, int pageSize, String studentName) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        Specification<ScoreCard> spec = (root, query, cb) -> {
            if (studentName != null) {
                return cb.equal(root.get("student").get("userName"), studentName);
            } else {
                return cb.conjunction();
            }
        };
        Page<ScoreCard> scoreCardsPage = scoreCardRepository.findAll(spec, pageable);
        List<ScoreCard> scoreCards = scoreCardsPage.getContent();
        return scoreCards.stream()
                .map(scoreCard -> {
                    ScoreCardModel model = new ScoreCardModel();
                    model.setSubject(scoreCard.getSubject().getSubjectName());
                    model.setMarksObtained(scoreCard.getScore());
                    model.setStudentName(scoreCard.getStudent().getUserName());
                    model.setMaxMarks(scoreCard.getMaxScore());
                    model.setCreatedAt(scoreCard.getCreatedAt());
                    return model;
                })
                .collect(Collectors.toList());
    }
}
