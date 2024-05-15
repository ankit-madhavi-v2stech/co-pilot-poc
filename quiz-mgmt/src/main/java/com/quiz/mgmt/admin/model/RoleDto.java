package com.quiz.mgmt.admin.model;

import lombok.Data;

import java.util.Date;

@Data
public class RoleDto {

    private int id;
    private String roleName;
    private Date createdAt;
    private Date updatedAt;

}
