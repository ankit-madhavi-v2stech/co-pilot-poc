package com.quiz.mgmt.test;

import com.quiz.mgmt.admin.controller.RolesController;
import com.quiz.mgmt.admin.model.RoleDto;
import com.quiz.mgmt.admin.service.RolesService;
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

public class RolesControllerTest {

    @InjectMocks
    private RolesController rolesController;

    @Mock
    private RolesService roleService;

    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(rolesController).build();
    }

    @Test
    public void testGetAllRoles() throws Exception {
        RoleDto roleDto = new RoleDto();
        roleDto.setId(1);
        roleDto.setRoleName("Admin");
        roleDto.setCreatedAt(new Date());
        roleDto.setUpdatedAt(new Date());

        List<RoleDto> roleDtoList = Arrays.asList(roleDto);

        when(roleService.getAllRoles()).thenReturn(roleDtoList);

        mockMvc.perform(get("/roles")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()", is(roleDtoList.size())));
    }

    @Test
    public void testGetRoleById() throws Exception {
        RoleDto roleDto = new RoleDto();
        roleDto.setId(1);
        roleDto.setRoleName("Admin");
        roleDto.setCreatedAt(new Date());
        roleDto.setUpdatedAt(new Date());

        when(roleService.getRoleById(any(Integer.class))).thenReturn(roleDto);

        mockMvc.perform(get("/roles/1")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.roleName", is("Admin")));
    }

    @Test
    public void testCreateRole() throws Exception {
        RoleDto roleDto = new RoleDto();
        roleDto.setId(1);
        roleDto.setRoleName("Admin");
        roleDto.setCreatedAt(new Date());
        roleDto.setUpdatedAt(new Date());

        when(roleService.createRole(any(RoleDto.class))).thenReturn(roleDto);

        mockMvc.perform(post("/roles")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"id\":1,\"roleName\":\"Admin\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.roleName", is("Admin")));
    }

    @Test
    public void testUpdateRole() throws Exception {
        RoleDto roleDto = new RoleDto();
        roleDto.setId(1);
        roleDto.setRoleName("Admin");
        roleDto.setCreatedAt(new Date());
        roleDto.setUpdatedAt(new Date());

        when(roleService.updateRole(any(RoleDto.class))).thenReturn(roleDto);

        mockMvc.perform(put("/roles")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"id\":1,\"roleName\":\"Admin\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.roleName", is("Admin")));
    }

    @Test
    public void testDeleteRole() throws Exception {
        mockMvc.perform(delete("/roles/1")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}
