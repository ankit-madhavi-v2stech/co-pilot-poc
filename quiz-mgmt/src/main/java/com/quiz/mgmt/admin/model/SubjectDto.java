package com.quiz.mgmt.admin.model;

import lombok.Data;
import java.util.Date;

@Data
public class SubjectDto {
    private int id;
    private String subjectName;
    private Date createdAt;
    private Date updatedAt;
}
