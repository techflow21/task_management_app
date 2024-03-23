package com.task.TaskManagementApp_API.controllers;
import com.task.TaskManagementApp_API.dtos.TaskRequest;
import com.task.TaskManagementApp_API.dtos.TaskResponse;
import com.task.TaskManagementApp_API.services.TaskService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@RestController
@CrossOrigin(origins = "http://localhost:4200")
@Tag(name="Task Management")
@RequestMapping("api/task")
public class TaskController {
    private TaskService taskService;
    @PostMapping("/create")
    public ResponseEntity<TaskResponse> createTask(@RequestBody TaskRequest newTask){
        return taskService.addTask(newTask);
    }

    @PutMapping("/update/{id}")
    public  ResponseEntity<TaskResponse> updateTask(@RequestBody TaskRequest taskToUpdate, @RequestParam Integer id){
        return taskService.updateTask(taskToUpdate, id);
    }

    @GetMapping("/getAll")
    public  ResponseEntity<List<TaskResponse>> getTasks(@RequestParam(required = false) String title){
        return taskService.getTasks();
    }

    @GetMapping("/getTaskById/{id}")
    public  ResponseEntity<TaskResponse> getTaskById(@PathVariable Integer id){
        return taskService.getTaskById(id);
    }

    @GetMapping("/getTasksByUserId/{userId}")
    public ResponseEntity<List<TaskResponse>> getTasksByUserId(@PathVariable Long userId){
        return taskService.getTasksByUserId(userId);
    }

    @PutMapping("/setReminder")
    public ResponseEntity<TaskResponse> setReminder(@RequestBody TaskRequest taskToUpdate, LocalDateTime reminder){
        return taskService.setReminder(taskToUpdate, reminder);
    }

    @PutMapping("/cancel")
    public ResponseEntity<TaskResponse> cancelTask(@RequestBody String status){
        return taskService.cancelTask(status);
    }

     @DeleteMapping("/delete")
     public  ResponseEntity<HttpStatus> deleteTask(@RequestParam Integer id){
      return taskService.deleteTaskById(id);

    }
}
