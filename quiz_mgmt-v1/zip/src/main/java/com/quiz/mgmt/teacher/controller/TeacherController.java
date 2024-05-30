package com.quiz.mgmt.teacher.controller;

import com.quiz.mgmt.student.model.ScoreCardModel;
import com.quiz.mgmt.teacher.model.QuestionModel;
import com.quiz.mgmt.teacher.service.ExcelOperationsService;
import com.quiz.mgmt.teacher.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/teacher")
public class TeacherController {

    @Autowired
    private ExcelOperationsService excelService;

    @Autowired
    private QuizService quizService;

    @GetMapping("/template/download")
    public ResponseEntity<Resource> downloadTemplate() {
        ByteArrayInputStream in = excelService.createTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment; filename=template.xlsx");

        return ResponseEntity
                .ok()
                .headers(headers)
                .body(new InputStreamResource(in));
    }

    @PostMapping("/upload/{subject}")
    public ResponseEntity<String> uploadFile(@PathVariable String subject, @RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return new ResponseEntity<>("Please select a file!", HttpStatus.OK);
        }

        if (!file.getOriginalFilename().endsWith(".xlsx")) {
            return new ResponseEntity<>("Invalid file type. Please upload an .xlsx file!", HttpStatus.BAD_REQUEST);
        }
        try {
            excelService.validateFile(file);
            List<QuestionModel> questionList = excelService.processFile(file);
            quizService.saveQuestionsAndAnswers(questionList, subject);
            return new ResponseEntity<>("File uploaded successfully!", HttpStatus.OK);
        } catch (IOException e) {
            return new ResponseEntity<>("Failed to upload file!", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/download/{subject}")
    public ResponseEntity<Resource> downloadSubjectFile(@PathVariable String subject) {
        ByteArrayInputStream in = excelService.createExcelForSubject(subject);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment; filename=" + subject + ".xlsx");

        return ResponseEntity
                .ok()
                .headers(headers)
                .body(new InputStreamResource(in));
    }

    @PostMapping("/question/{subjectName}")
    public ResponseEntity<QuestionModel> createQuestion(@PathVariable String subjectName, @RequestBody QuestionModel questionModel) {
        QuestionModel createdQuestion = quizService.createQuestion(subjectName, questionModel);
        return new ResponseEntity<>(createdQuestion, HttpStatus.CREATED);
    }

    @GetMapping("/question/{subjectName}")
    public ResponseEntity<List<QuestionModel>> getAllQuestions(@PathVariable String subjectName) {
        List<QuestionModel> questionModels = quizService.getAllQuestionsWithAnswers(subjectName);
        return new ResponseEntity<>(questionModels, HttpStatus.OK);
    }

    @PutMapping("/question/{subjectName}/{questionId}")
    public ResponseEntity<QuestionModel> updateQuestion(@PathVariable String subjectName, @PathVariable Long questionId, @RequestBody QuestionModel questionModel) {
        QuestionModel updatedQuestion = quizService.updateQuestion(subjectName, questionId, questionModel);
        return new ResponseEntity<>(updatedQuestion, HttpStatus.OK);
    }

    @DeleteMapping("/question/{subjectName}/{questionId}")
    public ResponseEntity<Void> deleteQuestion(@PathVariable String subjectName, @PathVariable Long questionId) {
        quizService.deleteQuestion(subjectName, questionId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/result/{pageNumber}/{pageSize}")
    public ResponseEntity<List<ScoreCardModel>> getScoreCards(@PathVariable int pageNumber, @PathVariable int pageSize, @RequestParam(required = false) String studentName) {
        List<ScoreCardModel> scoreCardModels = quizService.fetchResults(pageNumber - 1, pageSize, studentName);
        return ResponseEntity.ok(scoreCardModels);
    }
}
