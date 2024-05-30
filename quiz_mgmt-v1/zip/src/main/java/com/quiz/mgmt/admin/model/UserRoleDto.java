package com.quiz.mgmt.admin.model;

import lombok.Data;

import java.util.Date;

@Data
public class UserRoleDto {

    private int id;
    private UserDto user;
    private RoleDto role;
    private Date createdAt;
    private Date updatedAt;

}
