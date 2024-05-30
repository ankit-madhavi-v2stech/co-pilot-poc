package com.quiz.mgmt.model;

import com.quiz.mgmt.teacher.model.QuestionModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TestSubmissionModel {
    private List<QuestionModel> questions;
    private Integer userId;
}
