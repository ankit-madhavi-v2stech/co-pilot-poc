package com.quiz.mgmt.repository;

import com.quiz.mgmt.entity.Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface RolesRepository extends JpaRepository<Roles, Integer> {

    @Query("SELECT r FROM Roles r WHERE r.roleName = :roleName")
    Roles findByRoleName(String roleName);
}