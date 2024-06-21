package com.taskManager.api.controller;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;

import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.taskManager.api.dto.RequestDTO;
import com.taskManager.api.dto.ResponseDTO;
import com.taskManager.api.service.TaskService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

public class TaskControllerTest {

    private MockMvc mockMvc;

    @Mock
    private TaskService taskService;

    @InjectMocks
    private TaskController taskController;

    private ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        mockMvc = standaloneSetup(taskController).build();
    }

    @Test
    public void testGetAllTasks() throws Exception {
        when(taskService.getAllTasks()).thenReturn(List.of(new RequestDTO()));

        mockMvc.perform(get("/api/tasks"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$").isArray());
    }

    @Test
    public void testGetTaskById() throws Exception {
        Long taskId = 1L;
        RequestDTO task = new RequestDTO();
        when(taskService.getTaskById(taskId)).thenReturn(task);

        mockMvc.perform(get("/api/tasks/{id}", taskId))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$").exists());
    }

    @Test
    public void testCreateTask() throws Exception {
        RequestDTO requestDTO = new RequestDTO();
        ResponseDTO responseDTO = new ResponseDTO("Task created successfully", requestDTO);

        when(taskService.createTask(any(RequestDTO.class))).thenReturn(requestDTO);

        mockMvc.perform(post("/api/tasks")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDTO)))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.message").value("Task created successfully"));
    }

    @Test
    public void testUpdateTask() throws Exception {
        Long taskId = 1L;
        RequestDTO taskDTO = new RequestDTO();
        ResponseDTO responseDTO = new ResponseDTO("Task updated successfully", taskDTO);

        when(taskService.updateTask(eq(taskId), any(RequestDTO.class))).thenReturn(taskDTO);

        mockMvc.perform(put("/api/tasks/{id}", taskId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(taskDTO)))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.message").value("Task updated successfully"));
    }

    @Test
    public void testDeleteTask() throws Exception {
        Long taskId = 1L;
        doNothing().when(taskService).deleteTask(taskId);

        mockMvc.perform(delete("/api/tasks/{id}", taskId))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.message").value("Task deleted successfully"));
    }
}
