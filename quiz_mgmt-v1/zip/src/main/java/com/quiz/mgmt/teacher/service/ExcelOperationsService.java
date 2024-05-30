package com.quiz.mgmt.teacher.service;

import com.quiz.mgmt.teacher.model.AnswerModel;
import com.quiz.mgmt.teacher.model.QuestionModel;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class ExcelOperationsService {

    @Autowired
    private QuizService quizService;

    public ByteArrayInputStream createTemplate() {
        try (Workbook workbook = new XSSFWorkbook(); ByteArrayOutputStream out = new ByteArrayOutputStream()) {
            Sheet sheet = workbook.createSheet("Template");

            Row headerRow = sheet.createRow(0);
            headerRow.createCell(0).setCellValue("Question");
            headerRow.createCell(1).setCellValue("Answer");
            headerRow.createCell(2).setCellValue("Correct Answer");

            workbook.write(out);
            return new ByteArrayInputStream(out.toByteArray());
        } catch (IOException e) {
            throw new RuntimeException("Failed to create Excel file", e);
        }
    }

    public void validateFile(MultipartFile file) throws IOException {
        try (Workbook workbook = new XSSFWorkbook(file.getInputStream())) {
            Sheet sheet = workbook.getSheetAt(0);
            Row headerRow = sheet.getRow(0);

            if (headerRow == null || headerRow.getLastCellNum() < 3) {
                throw new RuntimeException("Invalid file format. Required columns: Question, Answer, Correct Answer");
            }

            if (!headerRow.getCell(0).getStringCellValue().equals("Question") ||
                    !headerRow.getCell(1).getStringCellValue().equals("Answer") ||
                    !headerRow.getCell(2).getStringCellValue().equals("Correct Answer")) {
                throw new RuntimeException("Invalid file format. Required columns: Question, Answer, Correct Answer");
            }

            for (Row row : sheet) {
                if (row.getRowNum() == 0) {
                    continue; // Skip the header row
                }

                Cell questionCell = row.getCell(0);
                Cell answerCell = row.getCell(1);
                Cell correctAnswerCell = row.getCell(2);

                if ((questionCell == null || questionCell.getStringCellValue().isEmpty()) ||
                        (answerCell == null || answerCell.getStringCellValue().isEmpty()) ||
                        (correctAnswerCell == null || correctAnswerCell.getStringCellValue().isEmpty())) {
                    throw new RuntimeException("All columns must have values. Empty cell found at row: " + (row.getRowNum() + 1));
                }
            }
        }
    }

    public List<QuestionModel> processFile(MultipartFile file) throws IOException {
        List<QuestionModel> questionList = new ArrayList<>();

        try (Workbook workbook = new XSSFWorkbook(file.getInputStream())) {
            Sheet sheet = workbook.getSheetAt(0);

            for (Row row : sheet) {
                if (row.getRowNum() == 0) {
                    continue; // Skip the header row
                }

                String question = row.getCell(0).getStringCellValue();
                String answer = row.getCell(1).getStringCellValue();
                Character correctAnswer = row.getCell(2).getStringCellValue().charAt(0);

                AnswerModel answerModel = new AnswerModel(answer, correctAnswer);

                QuestionModel existingQuestion = questionList.stream()
                        .filter(q -> q.getQuestion().equals(question))
                        .findFirst()
                        .orElse(null);

                if (existingQuestion != null) {
                    existingQuestion.getAnswers().add(answerModel);
                } else {
                    List<AnswerModel> answers = new ArrayList<>();
                    answers.add(answerModel);
                    QuestionModel questionModel = new QuestionModel(question, answers);
                    questionList.add(questionModel);
                }
            }
        }

        return questionList;
    }

    public ByteArrayInputStream createExcelForSubject(String subjectName) {
        try (Workbook workbook = new XSSFWorkbook(); ByteArrayOutputStream out = new ByteArrayOutputStream()) {
            Sheet sheet = workbook.createSheet(subjectName);
           List<QuestionModel> questionModels = quizService.getAllQuestionsWithAnswers(subjectName);
            Row headerRow = sheet.createRow(0);
            headerRow.createCell(0).setCellValue("Question");
            headerRow.createCell(1).setCellValue("Answer");
            headerRow.createCell(2).setCellValue("Correct Answer");

            // Write data rows
            int rowIndex = 1;
            for (QuestionModel questionModel : questionModels) {
                for (AnswerModel answerModel : questionModel.getAnswers()) {
                    Row row = sheet.createRow(rowIndex++);
                    row.createCell(0).setCellValue(questionModel.getQuestion());
                    row.createCell(1).setCellValue(answerModel.getAnswer());
                    row.createCell(2).setCellValue(answerModel.getCorrectAnswer().toString());
                }
            }
            workbook.write(out);
            return new ByteArrayInputStream(out.toByteArray());
        } catch (IOException e) {
            throw new RuntimeException("Failed to create Excel file", e);
        }
    }
}
