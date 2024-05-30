package com.quiz.mgmt.admin.model;

import lombok.Data;

import java.util.Date;

@Data
public class UserDto {

    private int id;
    private String userName;
    private Date createdAt;
    private Date updatedAt;

}
