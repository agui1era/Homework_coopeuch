package com.taskManager.api.service;

import com.taskManager.api.Entity.Task;
import com.taskManager.api.Repository.TaskRepository;
import com.taskManager.api.dto.RequestDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Override
    public List<RequestDTO> getAllTasks() {
        return taskRepository.findAll().stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    @Override
    public RequestDTO getTaskById(Long id) {
        Task task = taskRepository.findById(id).orElseThrow(() -> new RuntimeException("Task not found"));
        return convertToDTO(task);
    }

    @Override
    public RequestDTO createTask(RequestDTO taskDTO) {
        Task task = convertToEntity(taskDTO);
        task.setCreationDate(LocalDateTime.now());
        Task savedTask = taskRepository.save(task);
        return convertToDTO(savedTask);
    }

    @Override
    public RequestDTO updateTask(Long id, RequestDTO taskDTO) {
        Task task = taskRepository.findById(id).orElseThrow(() -> new RuntimeException("Task not found"));
        task.setDescription(taskDTO.getDescription());
        task.setActive(taskDTO.isActive());
        Task updatedTask = taskRepository.save(task);
        return convertToDTO(updatedTask);
    }

    @Override
    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }

    private RequestDTO convertToDTO(Task task) {
        RequestDTO taskDTO = new RequestDTO();
        taskDTO.setId(task.getId());
        taskDTO.setDescription(task.getDescription());
        taskDTO.setCreationDate(task.getCreationDate());
        taskDTO.setActive(task.isActive());
        return taskDTO;
    }

    private Task convertToEntity(RequestDTO taskDTO) {
        Task task = new Task();
        task.setDescription(taskDTO.getDescription());
        task.setActive(taskDTO.isActive());
        return task;
    }
}
