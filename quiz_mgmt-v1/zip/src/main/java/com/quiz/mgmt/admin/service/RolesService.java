package com.quiz.mgmt.admin.service;

import com.quiz.mgmt.admin.model.RoleDto;
import com.quiz.mgmt.entity.Roles;
import com.quiz.mgmt.repository.RolesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RolesService {

    @Autowired
    private RolesRepository rolesRepository;

    public List<RoleDto> getAllRoles() {
        return rolesRepository.findAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public RoleDto getRoleById(int id) {
        return convertToDto(rolesRepository.findById(id).orElse(null));
    }

    public RoleDto createRole(RoleDto rolesDto) {
        Roles roles = convertToEntity(rolesDto);
        roles.setCreatedAt(new Date());
        return convertToDto(rolesRepository.save(roles));
    }

    public RoleDto updateRole(RoleDto rolesDto) {
        Roles existingRoles = rolesRepository.findById(rolesDto.getId())
                .orElseThrow(() -> new RuntimeException("Role not found"));

        existingRoles.setRoleName(rolesDto.getRoleName());
        existingRoles.setUpdatedAt(new Date());

        return convertToDto(rolesRepository.save(existingRoles));
    }

    public void deleteRole(int id) {
        rolesRepository.deleteById(id);
    }

    public RoleDto getRoleByRoleName(String roleName) {
        return convertToDto(rolesRepository.findByRoleName(roleName));
    }

    private RoleDto convertToDto(Roles roles) {
        RoleDto rolesDto = new RoleDto();
        rolesDto.setId(roles.getId());
        rolesDto.setRoleName(roles.getRoleName());
        rolesDto.setCreatedAt(roles.getCreatedAt());
        rolesDto.setUpdatedAt(roles.getUpdatedAt());
        return rolesDto;
    }

    private Roles convertToEntity(RoleDto rolesDto) {
        Roles roles = new Roles();
        roles.setId(rolesDto.getId());
        roles.setRoleName(rolesDto.getRoleName());
        roles.setCreatedAt(rolesDto.getCreatedAt());
        roles.setUpdatedAt(rolesDto.getUpdatedAt());
        return roles;
    }
}