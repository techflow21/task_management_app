package com.task.TaskManagementApp_API.services.implimentations;

import com.task.TaskManagementApp_API.dtos.TaskRequest;
import com.task.TaskManagementApp_API.dtos.TaskResponse;
import com.task.TaskManagementApp_API.models.Task;
import com.task.TaskManagementApp_API.repositories.TaskRepository;
import com.task.TaskManagementApp_API.services.TaskService;
import com.task.TaskManagementApp_API.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TaskServiceImpl implements TaskService {
    private TaskRepository taskRepository;
    @Autowired
    private UserService userService;

    @Override
    public ResponseEntity<TaskResponse> addTask(TaskRequest newTask) {
        Task task = Task.builder()
                .title(newTask.getTitle())
                .description(newTask.getDescription())
                .priority(newTask.getPriority())
                .dueDate(newTask.getDueDate())
                .status(newTask.getStatus())
                .userId(userService.getLoggedInUser().getUserId())
                .reminder(newTask.getReminder())
                .build();
        Task savedTask = taskRepository.save(task);
        return ResponseEntity.ok(convertToTaskResponse(savedTask));
    }

    @Override
    public ResponseEntity<TaskResponse> updateTask(TaskRequest taskToUpdate, Integer id) {
        Optional<Task> optionalTask = taskRepository.findById(id);
        if (optionalTask.isPresent()) {
            Task existingTask = optionalTask.get();
            existingTask.setTitle(taskToUpdate.getTitle());
            existingTask.setDescription(taskToUpdate.getDescription());
            existingTask.setPriority(taskToUpdate.getPriority());
            existingTask.setDueDate(taskToUpdate.getDueDate());
            existingTask.setStatus(taskToUpdate.getStatus());
            existingTask.setReminder(taskToUpdate.getReminder());
            Task updatedTask = taskRepository.save(existingTask);
            return ResponseEntity.ok(convertToTaskResponse(updatedTask));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Override
    public ResponseEntity<List<TaskResponse>> getTasks() {
        List<Task> tasks = taskRepository.findAll();
        List<TaskResponse> taskResponses = tasks.stream()
                .map(this::convertToTaskResponse)
                .collect(Collectors.toList());
        return ResponseEntity.ok(taskResponses);
    }

    @Override
    public ResponseEntity<TaskResponse> getTaskById(Integer id) {
        Optional<Task> optionalTask = taskRepository.findById(id);
        return optionalTask.map(task -> ResponseEntity.ok(convertToTaskResponse(task)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Override
    public ResponseEntity<List<TaskResponse>> getTasksByUserId(Long userId) {
        List<Task> tasks = taskRepository.findByUserId(userId);
        List<TaskResponse> taskResponses = tasks.stream()
                .map(this::convertToTaskResponse)
                .collect(Collectors.toList());
        return ResponseEntity.ok(taskResponses);
    }

    @Override
    public ResponseEntity<TaskResponse> setReminder(TaskRequest taskToUpdate, LocalDateTime reminder) {
        // Implement logic to set a reminder for a task (This may vary based on the application's requirements)
        return null;
    }

    @Override
    public ResponseEntity<TaskResponse> cancelTask(String status) {
        // Implement logic to cancel a task (This may vary based on the application's requirements)
        return null;
    }

    @Override
    public ResponseEntity<HttpStatus> deleteTaskById(Integer id) {
        try {
            taskRepository.deleteById(id);
            return ResponseEntity.ok(HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private TaskResponse convertToTaskResponse(Task task) {
        return TaskResponse.builder()
                .id(task.getId())
                .title(task.getTitle())
                .description(task.getDescription())
                .priority(task.getPriority())
                .dueDate(task.getDueDate())
                .status(task.getStatus())
                .reminder(task.getReminder())
                .createdAt(task.getCreatedAt())
                .modifiedAt(task.getModifiedAt())
                .build();
    }
}

