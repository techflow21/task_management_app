package com.task.TaskManagementApp_API.services.implimentations;

import com.task.TaskManagementApp_API.dtos.TaskResponse;
import com.task.TaskManagementApp_API.dtos.UserInfo;
import com.task.TaskManagementApp_API.models.AppUser;
import com.task.TaskManagementApp_API.models.Role;
import com.task.TaskManagementApp_API.models.Task;
import com.task.TaskManagementApp_API.repositories.UserRepository;
import com.task.TaskManagementApp_API.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public ResponseEntity<List<UserInfo>> getUsers() {
        List<AppUser> users = userRepository.findAll();
        List<UserInfo> userInfos = users.stream()
                .map(this::convertToUserResponse)
                .collect(Collectors.toList());
        return ResponseEntity.ok(userInfos);
    }

    @Override
    public void assignRoleToUser(String email, Role role) {
        AppUser user = userRepository.findByEmail(email);
        if (user != null) {
            user.setRole(role.name().toUpperCase());
            userRepository.save(user);
        }
    }

    @Override
    public int getTotalNumberOfUsers() {
        return userRepository.findAll().size();
    }

    @Override
    public AppUser getLoggedInUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof UserDetails) {
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            return userRepository.findByEmail(userDetails.getUsername());
        }
        return null;
    }

    private UserInfo convertToUserResponse(AppUser user) {
        return UserInfo.builder()
                .userId(user.getUserId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .status(user.getStatus())
                .address(user.getAddress())
                .gender(user.getGender())
                .phoneNumber(user.getPhoneNumber())
                .createdAt(user.getCreatedAt())
                .modifiedAt(user.getModifiedAt())
                .build();
    }
}
