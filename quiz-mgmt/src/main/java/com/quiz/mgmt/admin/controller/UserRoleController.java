package com.quiz.mgmt.admin.controller;

import com.quiz.mgmt.admin.model.UserRoleDto;
import com.quiz.mgmt.admin.service.impl.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/userRoles")
public class UserRoleController {

    @Autowired
    private UserRoleService userRoleService;

    @GetMapping
    public ResponseEntity<List<UserRoleDto>> getAllUserRoles() {
        return ResponseEntity.ok(userRoleService.getAllUserRoles());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserRoleDto> getUserRoleById(@PathVariable int id) {
        return ResponseEntity.ok(userRoleService.getUserRoleById(id));
    }

    @PostMapping
    public ResponseEntity<UserRoleDto> createUserRole(@RequestBody UserRoleDto userRoleDto) {
        return ResponseEntity.ok(userRoleService.createUserRole(userRoleDto));
    }

    @PutMapping
    public ResponseEntity<UserRoleDto> updateUserRole(@RequestBody UserRoleDto userRoleDto) {
        return ResponseEntity.ok(userRoleService.updateUserRole(userRoleDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUserRole(@PathVariable int id) {
        userRoleService.deleteUserRole(id);
        return ResponseEntity.ok().build();
    }
}
