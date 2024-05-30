package com.quiz.mgmt.repository;

import com.quiz.mgmt.entity.ScoreCard;
import com.quiz.mgmt.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScoreCardRepository extends JpaRepository<ScoreCard, Integer>, JpaSpecificationExecutor<ScoreCard> {
    List<ScoreCard> findByStudent(User user);
}
