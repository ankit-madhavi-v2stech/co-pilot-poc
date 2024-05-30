package com.quiz.mgmt.student.controller;

import com.quiz.mgmt.entity.ScoreCard;
import com.quiz.mgmt.model.TestSubmissionModel;
import com.quiz.mgmt.student.model.Report;
import com.quiz.mgmt.student.model.ScoreCardModel;
import com.quiz.mgmt.teacher.model.QuestionModel;
import com.quiz.mgmt.teacher.service.ExcelOperationsService;
import com.quiz.mgmt.teacher.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayInputStream;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private QuizService quizService;

    @GetMapping("/exam/{subjectName}")
    public ResponseEntity<List<QuestionModel>> examQuestions(@PathVariable String subjectName) {
        List<QuestionModel> questions = quizService.getRandomQuestions(subjectName);
        return ResponseEntity.ok(questions);
    }

    @PostMapping("/submit-test")
    public ResponseEntity<Report> submitTest(@RequestBody TestSubmissionModel testSubmissionModel) {
        Report report = quizService.getCorrectAnswers(testSubmissionModel);
        return ResponseEntity.ok(report);
    }

    @GetMapping("/result/{userId}")
    public ResponseEntity<List<ScoreCardModel>> getScoreCards(@PathVariable Integer userId) {
        List<ScoreCardModel> scoreCardModels = quizService.fetchResultsForUser(userId);
        return ResponseEntity.ok(scoreCardModels);
    }
}
