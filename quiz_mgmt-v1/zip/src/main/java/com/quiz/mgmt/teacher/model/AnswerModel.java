package com.quiz.mgmt.teacher.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AnswerModel {

    private String answer;
    private Character correctAnswer;
}
