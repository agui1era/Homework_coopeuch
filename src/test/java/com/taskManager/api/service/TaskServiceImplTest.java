package com.taskManager.api.service;

import com.taskManager.api.Entity.Task;
import com.taskManager.api.Repository.TaskRepository;
import com.taskManager.api.dto.RequestDTO;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.junit.jupiter.api.BeforeEach;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class TaskServiceImplTest {

    @Mock
    private TaskRepository taskRepository;

    @InjectMocks
    private TaskServiceImpl taskService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllTasks() {
        Task task1 = new Task();
        task1.setId(1L);
        Task task2 = new Task();
        task2.setId(2L);
        when(taskRepository.findAll()).thenReturn(Arrays.asList(task1, task2));

        List<RequestDTO> result = taskService.getAllTasks();

        assertEquals(2, result.size());
        verify(taskRepository).findAll();
    }

    @Test
    public void testGetTaskById() {
        Task task = new Task();
        task.setId(1L);
        when(taskRepository.findById(1L)).thenReturn(Optional.of(task));

        RequestDTO result = taskService.getTaskById(1L);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        verify(taskRepository).findById(1L);
    }

    @Test
    public void testCreateTask() {
        RequestDTO requestDTO = new RequestDTO();
        requestDTO.setDescription("New Task");

        Task task = new Task();
        task.setDescription("New Task");

        when(taskRepository.save(any(Task.class))).thenReturn(task);

        RequestDTO savedDTO = taskService.createTask(requestDTO);

        assertNotNull(savedDTO);
        verify(taskRepository).save(any(Task.class));
    }

    @Test
    public void testUpdateTask() {
        Task existingTask = new Task();
        existingTask.setId(1L);
        existingTask.setDescription("Old Description");

        when(taskRepository.findById(1L)).thenReturn(Optional.of(existingTask));
        when(taskRepository.save(any(Task.class))).thenReturn(existingTask);

        RequestDTO requestDTO = new RequestDTO();
        requestDTO.setDescription("Updated Description");

        RequestDTO updatedDTO = taskService.updateTask(1L, requestDTO);

        assertNotNull(updatedDTO);
        assertEquals("Updated Description", updatedDTO.getDescription());
        verify(taskRepository).save(any(Task.class));
        verify(taskRepository).findById(1L);
    }

    @Test
    public void testDeleteTask() {
        doNothing().when(taskRepository).deleteById(1L);

        taskService.deleteTask(1L);

        verify(taskRepository).deleteById(1L);
    }
}
