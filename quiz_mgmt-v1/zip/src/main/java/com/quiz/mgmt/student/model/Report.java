package com.quiz.mgmt.student.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Report {
    private List<QuestionStatusModel> questionStatusModels;
    private Integer marks;
}
