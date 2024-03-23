package com.task.TaskManagementApp_API.services;

import com.task.TaskManagementApp_API.dtos.UserInfo;
import com.task.TaskManagementApp_API.models.AppUser;
import com.task.TaskManagementApp_API.models.Role;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UserService {
    ResponseEntity<List<UserInfo>> getUsers();
    void assignRoleToUser(String email, Role role);
    int getTotalNumberOfUsers();
    AppUser getLoggedInUser();
}
