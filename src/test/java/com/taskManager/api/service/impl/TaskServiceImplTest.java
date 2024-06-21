package com.taskManager.api.service.impl;

import com.taskManager.api.Entity.Task;
import com.taskManager.api.Repository.TaskRepository;
import com.taskManager.api.dto.RequestDTO;
import com.taskManager.api.service.TaskServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

public class TaskServiceImplTest {

    @Mock
    private TaskRepository taskRepository;

    @InjectMocks
    private TaskServiceImpl taskService;

    private Task task;
    private RequestDTO taskDTO;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        task = new Task(1L, "Test Task", LocalDateTime.now(), true);
        taskDTO = new RequestDTO();
        taskDTO.setId(1L);
        taskDTO.setDescription("Test Task");
        taskDTO.setCreationDate(LocalDateTime.now());
        taskDTO.setActive(true);
    }

    @Test
    public void testGetAllTasks() {
        List<Task> tasks = new ArrayList<>();
        tasks.add(task);
        when(taskRepository.findAll()).thenReturn(tasks);

        List<RequestDTO> taskDTOs = taskService.getAllTasks();

        assertEquals(1, taskDTOs.size());
        verify(taskRepository, times(1)).findAll();
    }

    @Test
    public void testGetTaskById() {
        when(taskRepository.findById(anyLong())).thenReturn(Optional.of(task));

        RequestDTO foundTask = taskService.getTaskById(1L);

        assertEquals(task.getDescription(), foundTask.getDescription());
        verify(taskRepository, times(1)).findById(anyLong());
    }

    @Test
    public void testCreateTask() {
        when(taskRepository.save(any(Task.class))).thenReturn(task);

        RequestDTO createdTask = taskService.createTask(taskDTO);

        assertEquals(task.getDescription(), createdTask.getDescription());
        verify(taskRepository, times(1)).save(any(Task.class));
    }

    @Test
    public void testUpdateTask() {
        when(taskRepository.findById(anyLong())).thenReturn(Optional.of(task));
        when(taskRepository.save(any(Task.class))).thenReturn(task);

        RequestDTO updatedTask = taskService.updateTask(1L, taskDTO);

        assertEquals(task.getDescription(), updatedTask.getDescription());
        verify(taskRepository, times(1)).findById(anyLong());
        verify(taskRepository, times(1)).save(any(Task.class));
    }

    @Test
    public void testDeleteTask() {
        doNothing().when(taskRepository).deleteById(anyLong());

        taskService.deleteTask(1L);

        verify(taskRepository, times(1)).deleteById(anyLong());
    }
}
