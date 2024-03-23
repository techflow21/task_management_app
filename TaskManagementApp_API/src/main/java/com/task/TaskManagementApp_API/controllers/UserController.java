package com.task.TaskManagementApp_API.controllers;

import com.task.TaskManagementApp_API.dtos.UserInfo;
import com.task.TaskManagementApp_API.models.Role;
import com.task.TaskManagementApp_API.services.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Tag(name="User Management")
@CrossOrigin(origins = "http://localhost:4200")

@RequestMapping("api/user")
public class UserController {
    private UserService userService;

    @GetMapping("/getAll")
    public ResponseEntity<List<UserInfo>> getUsers(){
        return userService.getUsers();
    }

    @GetMapping("/totalUsers")
    public int getTotalUsers(){
        return userService.getTotalNumberOfUsers();
    }

    @GetMapping("/assignRole")
    public void assignRole(@RequestBody Role role, String email){
        userService.assignRoleToUser(email, role);
    }
}
