package com.quiz.mgmt.test;

import com.quiz.mgmt.admin.controller.UserController;
import com.quiz.mgmt.admin.model.UserDto;
import com.quiz.mgmt.admin.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class UserControllerTest {

    @InjectMocks
    private UserController userController;

    @Mock
    private UserService userService;

    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
    }

    @Test
    public void testGetAllUsers() throws Exception {
        UserDto userDto = new UserDto();
        userDto.setId(1);
        userDto.setUserName("Admin");
        userDto.setCreatedAt(new Date());
        userDto.setUpdatedAt(new Date());

        List<UserDto> userDtoList = Arrays.asList(userDto);

        when(userService.getAllUsers()).thenReturn(userDtoList);

        mockMvc.perform(get("/users")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()", is(userDtoList.size())));
    }

    @Test
    public void testGetUserById() throws Exception {
        UserDto userDto = new UserDto();
        userDto.setId(1);
        userDto.setUserName("Admin");
        userDto.setCreatedAt(new Date());
        userDto.setUpdatedAt(new Date());

        when(userService.getUserById(any(Integer.class))).thenReturn(userDto);

        mockMvc.perform(get("/users/1")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.userName", is("Admin")));
    }

    @Test
    public void testCreateUser() throws Exception {
        UserDto userDto = new UserDto();
        userDto.setId(1);
        userDto.setUserName("Admin");
        userDto.setCreatedAt(new Date());
        userDto.setUpdatedAt(new Date());

        when(userService.createUser(any(UserDto.class))).thenReturn(userDto);

        mockMvc.perform(post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"id\":1,\"userName\":\"Admin\",\"password\":\"password\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.userName", is("Admin")));
    }

    @Test
    public void testUpdateUser() throws Exception {
        UserDto userDto = new UserDto();
        userDto.setId(1);
        userDto.setUserName("Admin");
        userDto.setCreatedAt(new Date());
        userDto.setUpdatedAt(new Date());

        when(userService.updateUser(any(UserDto.class))).thenReturn(userDto);

        mockMvc.perform(put("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"id\":1,\"userName\":\"Admin\",\"password\":\"password\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.userName", is("Admin")));
    }

    @Test
    public void testDeleteUser() throws Exception {
        mockMvc.perform(delete("/users/1")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}
