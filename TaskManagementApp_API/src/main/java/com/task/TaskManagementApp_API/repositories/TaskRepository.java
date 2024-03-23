package com.task.TaskManagementApp_API.repositories;

import com.task.TaskManagementApp_API.models.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Integer> {
    List<Task> findByUserId(Long userId);
}
