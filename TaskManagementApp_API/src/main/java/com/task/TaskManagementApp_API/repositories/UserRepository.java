package com.task.TaskManagementApp_API.repositories;

import com.task.TaskManagementApp_API.models.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<AppUser, Long> {
    AppUser findByEmail(String email);
}
