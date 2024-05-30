package com.quiz.mgmt.student.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuestionStatusModel {
    private Integer questionId;
    private String question;
    private String correctAnswer;
    private String submittedAnswer;
    private Boolean status;
}
