package com.task.TaskManagementApp_API.services;

import com.task.TaskManagementApp_API.dtos.TaskRequest;
import com.task.TaskManagementApp_API.dtos.TaskResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.time.LocalDateTime;
import java.util.List;

public interface TaskService {
    ResponseEntity<TaskResponse> addTask(TaskRequest newTask);
    ResponseEntity<TaskResponse> updateTask(TaskRequest taskToUpdate, Integer id);
    ResponseEntity<List<TaskResponse>> getTasks();
    ResponseEntity<TaskResponse> getTaskById(Integer id);
    ResponseEntity<List<TaskResponse>> getTasksByUserId(Long userId);
    ResponseEntity<TaskResponse> setReminder(TaskRequest taskToUpdate, LocalDateTime reminder);
    ResponseEntity<TaskResponse> cancelTask(String status);

    ResponseEntity<HttpStatus> deleteTaskById(Integer id);

    //ResponseEntity<StatusCode> deleteTask(Integer id);
}
