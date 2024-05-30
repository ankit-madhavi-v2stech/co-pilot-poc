package com.quiz.mgmt.repository;

import com.quiz.mgmt.entity.QuizQuestion;
import com.quiz.mgmt.entity.Subject;
import com.quiz.mgmt.teacher.model.QuestionModel;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface QuizQuestionRepository extends JpaRepository<QuizQuestion, Integer> {
    List<QuizQuestion> findBySubject(Subject subject);

    Optional<QuizQuestion> findByIdAndSubject(Integer questionId, Subject subject);

    @Query(value = "SELECT q.* FROM quiz_question q inner join subject s on q.subject_id = s.id WHERE s.subject_name = ?1 ORDER BY RAND()", nativeQuery = true)
    List<QuizQuestion> findRandomQuestions(String subjectName, Pageable pageable);
}
