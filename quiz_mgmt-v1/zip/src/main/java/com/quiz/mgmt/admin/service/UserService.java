package com.quiz.mgmt.admin.service;

import com.quiz.mgmt.admin.model.UserDto;
import com.quiz.mgmt.entity.User;
import com.quiz.mgmt.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<UserDto> getAllUsers() {
        return userRepository.findAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public UserDto getUserById(int id) {
        return convertToDto(userRepository.findById(id).orElse(null));
    }

    public UserDto createUser(UserDto userDto) {
        User user = convertToEntity(userDto);
        user.setCreatedAt(new Date());
        return convertToDto(userRepository.save(user));
    }

    public UserDto updateUser(UserDto userDto) {
        User existingUser = userRepository.findById(userDto.getId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        existingUser.setUserName(userDto.getUserName());
        existingUser.setUpdatedAt(new Date());

        return convertToDto(userRepository.save(existingUser));
    }

    public void deleteUser(int id) {
        userRepository.deleteById(id);
    }

    public UserDto getUserByUserName(String userName) {
        return convertToDto(userRepository.findByUserName(userName).get());
    }

    private UserDto convertToDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setUserName(user.getUserName());
        userDto.setCreatedAt(user.getCreatedAt());
        userDto.setUpdatedAt(user.getUpdatedAt());
        return userDto;
    }

    private User convertToEntity(UserDto userDto) {
        User user = new User();
        user.setId(userDto.getId());
        user.setUserName(userDto.getUserName());
        user.setCreatedAt(userDto.getCreatedAt());
        user.setUpdatedAt(userDto.getUpdatedAt());
        return user;
    }
}
