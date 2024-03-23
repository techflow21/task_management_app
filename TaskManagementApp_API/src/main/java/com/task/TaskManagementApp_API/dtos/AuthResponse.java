package com.task.TaskManagementApp_API.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuthResponse {
    private Integer statusCode;
    private String responseMessage;
    private UserInfo userInfo;
}
