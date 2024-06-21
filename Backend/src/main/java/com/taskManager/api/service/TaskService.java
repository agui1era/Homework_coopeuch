package com.taskManager.api.service;

import java.util.List;

import com.taskManager.api.dto.RequestDTO;

public interface TaskService {
    List<RequestDTO> getAllTasks();
    RequestDTO getTaskById(Long id);
    RequestDTO createTask(RequestDTO taskDTO);
    RequestDTO updateTask(Long id, RequestDTO taskDTO);
    void deleteTask(Long id);
}
