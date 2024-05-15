package com.quiz.mgmt.admin.service.impl;

import com.quiz.mgmt.admin.model.RoleDto;
import com.quiz.mgmt.admin.model.UserDto;
import com.quiz.mgmt.admin.model.UserRoleDto;
import com.quiz.mgmt.entity.Roles;
import com.quiz.mgmt.entity.User;
import com.quiz.mgmt.entity.UserRole;
import com.quiz.mgmt.repository.RolesRepository;
import com.quiz.mgmt.repository.UserRepository;
import com.quiz.mgmt.repository.UserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserRoleService {

    @Autowired
    private UserRoleRepository userRoleRepository;

    @Autowired
    private RolesRepository roleRepository;

    @Autowired
    private UserRepository userRepository;

    public List<UserRoleDto> getAllUserRoles() {
        return userRoleRepository.findAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public UserRoleDto getUserRoleById(int id) {
        return convertToDto(userRoleRepository.findById(id).orElse(null));
    }

    public UserRoleDto createUserRole(UserRoleDto userRoleDto) {
        UserRole userRole = convertToEntity(userRoleDto);
        userRole.setCreatedAt(new Date());
        return convertToDto(userRoleRepository.save(userRole));
    }

    public UserRoleDto updateUserRole(UserRoleDto userRoleDto) {
        UserRole existingUserRole = userRoleRepository.findById(userRoleDto.getId())
                .orElseThrow(() -> new RuntimeException("UserRole not found"));

        existingUserRole.setRole(convertRoleDtoToEntity(userRoleDto.getRole()));
        existingUserRole.setUser(convertUserDtoToEntity(userRoleDto.getUser()));
        existingUserRole.setUpdatedAt(new Date());

        return convertToDto(userRoleRepository.save(existingUserRole));
    }

    public void deleteUserRole(int id) {
        userRoleRepository.deleteById(id);
    }

    private UserRoleDto convertToDto(UserRole userRole) {
        UserRoleDto userRoleDto = new UserRoleDto();
        userRoleDto.setId(userRole.getId());
        userRoleDto.setUser(convertUserToDto(userRole.getUser()));
        userRoleDto.setRole(convertRoleToDto(userRole.getRole()));
        userRoleDto.setCreatedAt(userRole.getCreatedAt());
        userRoleDto.setUpdatedAt(userRole.getUpdatedAt());
        return userRoleDto;
    }

    private UserDto convertUserToDto(User user) {
        UserDto userDto = new UserDto();
        // Set the properties from the User entity to UserDto
        // For example:
        // userDto.setId(user.getId());
        // userDto.setName(user.getName());
        // ...
        return userDto;
    }

    private RoleDto convertRoleToDto(Roles role) {
        RoleDto roleDto = new RoleDto();
        // Set the properties from the Roles entity to RoleDto
        // For example:
        // roleDto.setId(role.getId());
        // roleDto.setRoleName(role.getRoleName());
        // ...
        return roleDto;
    }

    private UserRole convertToEntity(UserRoleDto userRoleDto) {
        UserRole userRole = new UserRole();
        userRole.setId(userRoleDto.getId());
        userRole.setUser(convertUserDtoToEntity(userRoleDto.getUser()));
        userRole.setRole(convertRoleDtoToEntity(userRoleDto.getRole()));
        userRole.setCreatedAt(userRoleDto.getCreatedAt());
        userRole.setUpdatedAt(userRoleDto.getUpdatedAt());
        return userRole;
    }

    private Roles convertRoleDtoToEntity(RoleDto roleDto) {
        // Fetch the Role entity from the database using the id from the RoleDto
        return roleRepository.findById(roleDto.getId())
                .orElseThrow(() -> new RuntimeException("Role not found"));
    }

    private User convertUserDtoToEntity(UserDto userDto) {
        // Fetch the User entity from the database using the id from the UserDto
        return userRepository.findById(userDto.getId())
                .orElseThrow(() -> new RuntimeException("User not found"));
    }
}
