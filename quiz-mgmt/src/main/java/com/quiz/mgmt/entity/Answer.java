// com.quiz.mgmt.entity.Answer.java
package com.quiz.mgmt.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Table(name = "answers")
@Data
public class Answer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "answer")
    private String answer;

    @Column(name = "is_correct")
    private boolean isCorrect;

    @ManyToOne
    @JoinColumn(name = "question_id")
    private QuizQuestion question;

    @Column(name = "created_at")
    private Date createdAt;

    @Column(name = "updated_at")
    private Date updatedAt;

}