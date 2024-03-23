package com.task.TaskManagementApp_API.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class TaskRequest {
    private String title;
    private String description;
    private String priority;
    private LocalDateTime dueDate;
    private String status;
    private LocalDateTime reminder;
}
