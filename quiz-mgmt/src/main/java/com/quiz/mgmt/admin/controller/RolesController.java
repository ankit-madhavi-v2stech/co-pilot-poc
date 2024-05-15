package com.quiz.mgmt.admin.controller;

import com.quiz.mgmt.admin.model.RoleDto;
import com.quiz.mgmt.admin.service.impl.RolesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/roles")
public class RolesController {

    @Autowired
    private RolesService rolesService;

    @GetMapping
    public List<RoleDto> getAllRoles() {
        return rolesService.getAllRoles();
    }

    @GetMapping("/{id}")
    public RoleDto getRoleById(@PathVariable int id) {
        return rolesService.getRoleById(id);
    }

    @PostMapping
    public RoleDto createRole(@RequestBody RoleDto rolesDto) {
        return rolesService.createRole(rolesDto);
    }

    @PutMapping
    public RoleDto updateRole(@RequestBody RoleDto rolesDto) {
        return rolesService.updateRole(rolesDto);
    }

    @DeleteMapping("/{id}")
    public void deleteRole(@PathVariable int id) {
        rolesService.deleteRole(id);
    }

    @GetMapping("/rolename/{roleName}")
    public RoleDto getRoleByRoleName(@PathVariable String roleName) {
        return rolesService.getRoleByRoleName(roleName);
    }
}
