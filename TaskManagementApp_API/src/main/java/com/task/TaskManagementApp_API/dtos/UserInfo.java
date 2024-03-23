package com.task.TaskManagementApp_API.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserInfo {
    private Long userId;
    private String firstName;
    private String lastName;
    private String otherName;
    private String email;
    private String gender;
    private String phoneNumber;
    private String address;
    private String status;
    private String role;
    private String token;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
    private String photoUrl;
}
