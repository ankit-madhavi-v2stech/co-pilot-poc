package com.quiz.mgmt.repository;

import com.quiz.mgmt.entity.Answer;
import com.quiz.mgmt.entity.QuizQuestion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AnswerRepository extends JpaRepository<Answer, Integer> {
    List<Answer> findByQuestion(QuizQuestion quizQuestion);

    Optional<Answer> findByQuestionAndCorrect(QuizQuestion quizQuestion, boolean b);
}
