package com.task.TaskManagementApp_API.controllers;
import com.task.TaskManagementApp_API.dtos.AuthResponse;
import com.task.TaskManagementApp_API.dtos.LoginRequest;
import com.task.TaskManagementApp_API.dtos.RegisterRequest;
import com.task.TaskManagementApp_API.services.AuthService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@Tag(name="Authentication Management")
@RequestMapping("api/auth")
public class AuthenticationController {
    @Autowired
    private AuthService authService;
    @PostMapping("/register")
    public ResponseEntity<AuthResponse>registerUser(@RequestBody RegisterRequest registerRequest){
        return authService.registerUser(registerRequest);
    }

    @PostMapping("/login")
    public  ResponseEntity<AuthResponse>login(@RequestBody LoginRequest loginRequest){
        return authService.loginUser(loginRequest);
    }

}
