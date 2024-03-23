package com.task.TaskManagementApp_API.services.implimentations;

import com.task.TaskManagementApp_API.authConfig.JwtTokenProvider;
import com.task.TaskManagementApp_API.dtos.AuthResponse;
import com.task.TaskManagementApp_API.dtos.LoginRequest;
import com.task.TaskManagementApp_API.dtos.RegisterRequest;
import com.task.TaskManagementApp_API.dtos.UserInfo;
import com.task.TaskManagementApp_API.models.AppUser;
import com.task.TaskManagementApp_API.models.Role;
import com.task.TaskManagementApp_API.repositories.AuthRepository;
import com.task.TaskManagementApp_API.services.AuthService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class AuthServiceImpl implements AuthService {
    private static final Logger logger = LoggerFactory.getLogger(AuthServiceImpl.class);
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private AuthRepository authRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Override
    public ResponseEntity<AuthResponse> registerUser(RegisterRequest registerRequest) {
        if(authRepository.findByEmail(registerRequest.getEmail()).isPresent()){
            return ResponseEntity.badRequest().body(
                    AuthResponse.builder()
                            .statusCode(400)
                            .responseMessage("User with the info provided already exists!")
                            .userInfo(null)
                            .build()
            );
        }
        AppUser newUser = AppUser.builder()
                .firstName(registerRequest.getFirstName())
                .lastName(registerRequest.getLastName())
                .otherName(registerRequest.getOtherName())
                .email(registerRequest.getEmail())
                .password(passwordEncoder.encode(registerRequest.getPassword()))
                .address(registerRequest.getAddress())
                .phoneNumber(registerRequest.getPhoneNumber())
                .gender(registerRequest.getGender())
                .status("ACTIVE")
                .role(Role.USER.name().toUpperCase())
                .build();

        AppUser savedUser = authRepository.save(newUser);

        return ResponseEntity.ok().body(
                AuthResponse.builder()
                        .statusCode(200)
                        .responseMessage("Registration Successful!")
                        .userInfo(modelMapper.map(savedUser, UserInfo.class))
                        .build()
        );
    }

    @Override
    public ResponseEntity<AuthResponse> loginUser(LoginRequest loginRequest) {

        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));

        var user = authRepository.findByEmail(loginRequest.getEmail()).orElseThrow();

        var jwt = jwtTokenProvider.generateToken(user);
        var refreshToken = jwtTokenProvider.generateRefreshToken(new HashMap<>(), user);

        user.setToken(jwt);

        authRepository.save(user);

        return ResponseEntity.ok().body(
                AuthResponse.builder()
                        .statusCode(200)
                        .responseMessage("JWT: " + jwt + " " + "Refresh Token: " + refreshToken)
                        .userInfo(modelMapper.map(user, UserInfo.class))
                        .build()
        );
    }
    @Override
    public AuthResponse logoutUser() {
        return null;
    }
}
