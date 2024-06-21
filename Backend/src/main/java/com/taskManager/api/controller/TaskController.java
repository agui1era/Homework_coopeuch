package com.taskManager.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.taskManager.api.dto.ResponseDTO;
import com.taskManager.api.dto.RequestDTO;
import com.taskManager.api.exception.ResourceNotFoundException;
import com.taskManager.api.service.TaskService;

import java.util.List;

@RestController
@CrossOrigin 
@RequestMapping("/api/tasks")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @GetMapping
    public List<RequestDTO> getAllTasks() {
        return taskService.getAllTasks();
    }

    @GetMapping("/{id}")
    public RequestDTO getTaskById(@PathVariable Long id) {
        RequestDTO task = taskService.getTaskById(id);
        if (task == null) {
            throw new ResourceNotFoundException("Task not found with id " + id);
        }
        return task;
    }

    @PostMapping
    public ResponseDTO createTask(@RequestBody RequestDTO taskDTO) {
        RequestDTO createdTask = taskService.createTask(taskDTO);
        return new ResponseDTO("Task created successfully", createdTask);
    }

    @PutMapping("/{id}")
    public ResponseDTO updateTask(@PathVariable Long id, @RequestBody RequestDTO taskDTO) {
        RequestDTO updatedTask = taskService.updateTask(id, taskDTO);
        if (updatedTask == null) {
            throw new ResourceNotFoundException("Task not found with id " + id);
        }
        return new ResponseDTO("Task updated successfully", updatedTask);
    }

    @DeleteMapping("/{id}")
    public ResponseDTO deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
        return new ResponseDTO("Task deleted successfully", null);
    }
}
