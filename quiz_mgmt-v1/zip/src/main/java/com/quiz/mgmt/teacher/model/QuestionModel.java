package com.quiz.mgmt.teacher.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuestionModel {

    private Integer questionId;

    private String question;

    private List<AnswerModel> answers;

    public QuestionModel(String question, List<AnswerModel> answers) {
        this.question = question;
        this.answers = answers;
    }
}
