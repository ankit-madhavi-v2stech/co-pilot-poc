package com.quiz.mgmt.student.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ScoreCardModel {
    private String studentName;
    private String subject;
    private int marksObtained;
    private int maxMarks;
    private Date createdAt;

}
