package com.quiz.mgmt.admin.controller;

import com.quiz.mgmt.admin.model.UserDto;
import com.quiz.mgmt.admin.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserServiceImpl userService;

    @GetMapping
    public List<UserDto> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public UserDto getUserById(@PathVariable int id) {
        return userService.getUserById(id);
    }

    @PostMapping
    public UserDto createUser(@RequestBody UserDto userDto) {
        return userService.createUser(userDto);
    }

    @PutMapping
    public UserDto updateUser(@RequestBody UserDto userDto) {
        return userService.updateUser(userDto);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable int id) {
        userService.deleteUser(id);
    }

    @GetMapping("/username/{userName}")
    public UserDto getUserByUserName(@PathVariable String userName) {
        return userService.getUserByUserName(userName);
    }
}
