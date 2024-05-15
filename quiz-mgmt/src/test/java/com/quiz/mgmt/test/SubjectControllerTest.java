package com.quiz.mgmt.test;

import com.quiz.mgmt.admin.controller.SubjectController;
import com.quiz.mgmt.admin.model.SubjectDto;
import com.quiz.mgmt.admin.service.impl.SubjectService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class SubjectControllerTest {

    @InjectMocks
    private SubjectController subjectController;

    @Mock
    private SubjectService subjectService;

    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(subjectController).build();
    }

    @Test
    public void testGetAllSubjects() throws Exception {
        SubjectDto subjectDto = new SubjectDto();
        subjectDto.setId(1);
        subjectDto.setSubjectName("Math");
        subjectDto.setCreatedAt(new Date());
        subjectDto.setUpdatedAt(new Date());

        List<SubjectDto> subjectDtoList = Arrays.asList(subjectDto);

        when(subjectService.getAllSubjects()).thenReturn(subjectDtoList);

        mockMvc.perform(get("/api/subjects")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()", is(subjectDtoList.size())));
    }

    @Test
    public void testGetSubjectById() throws Exception {
        SubjectDto subjectDto = new SubjectDto();
        subjectDto.setId(1);
        subjectDto.setSubjectName("Math");
        subjectDto.setCreatedAt(new Date());
        subjectDto.setUpdatedAt(new Date());

        when(subjectService.getSubjectById(any(Integer.class))).thenReturn(subjectDto);

        mockMvc.perform(get("/api/subjects/1")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.subjectName", is("Math")));
    }

    @Test
    public void testCreateSubject() throws Exception {
        SubjectDto subjectDto = new SubjectDto();
        subjectDto.setId(1);
        subjectDto.setSubjectName("Math");
        subjectDto.setCreatedAt(new Date());
        subjectDto.setUpdatedAt(new Date());

        when(subjectService.createSubject(any(SubjectDto.class))).thenReturn(subjectDto);

        mockMvc.perform(post("/api/subjects")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"id\":1,\"subjectName\":\"Math\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.subjectName", is("Math")));
    }

    @Test
    public void testUpdateSubject() throws Exception {
        SubjectDto subjectDto = new SubjectDto();
        subjectDto.setId(1);
        subjectDto.setSubjectName("Math");
        subjectDto.setCreatedAt(new Date());
        subjectDto.setUpdatedAt(new Date());

        when(subjectService.updateSubject(any(SubjectDto.class))).thenReturn(subjectDto);

        mockMvc.perform(put("/api/subjects")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"id\":1,\"subjectName\":\"Math\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.subjectName", is("Math")));
    }

    @Test
    public void testDeleteSubject() throws Exception {
        mockMvc.perform(delete("/api/subjects/1")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}