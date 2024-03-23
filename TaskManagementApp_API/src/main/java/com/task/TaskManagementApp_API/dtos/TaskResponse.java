package com.task.TaskManagementApp_API.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class TaskResponse {
    private Integer id;
    private String title;
    private String description;
    private String priority;
    private LocalDateTime dueDate;
    private String status;
    private LocalDateTime reminder;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
}
