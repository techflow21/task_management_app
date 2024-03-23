package com.task.TaskManagementApp_API.services;

import com.task.TaskManagementApp_API.dtos.AuthResponse;
import com.task.TaskManagementApp_API.dtos.LoginRequest;
import com.task.TaskManagementApp_API.dtos.RegisterRequest;
import org.springframework.http.ResponseEntity;

public interface AuthService {
    ResponseEntity<AuthResponse> registerUser(RegisterRequest registerRequest);
    ResponseEntity<AuthResponse> loginUser(LoginRequest loginRequest);
    AuthResponse logoutUser();

}
