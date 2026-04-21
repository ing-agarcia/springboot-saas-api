package com.eduardo.springbootsaasapi.modules.auth.application.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.eduardo.springbootsaasapi.modules.auth.application.dto.AuthResponse;
import com.eduardo.springbootsaasapi.modules.auth.application.dto.LoginRequest;
import com.eduardo.springbootsaasapi.modules.user.application.dto.UserDetailDTO;
import com.eduardo.springbootsaasapi.modules.user.application.services.UserService;
import com.eduardo.springbootsaasapi.modules.user.domain.entities.User;
import com.eduardo.springbootsaasapi.shared.infrastructure.config.JwtUtil;
import com.eduardo.springbootsaasapi.shared.infrastructure.exceptions.NotFoundException;

@Service
public class AuthService {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public AuthService(UserService userService, PasswordEncoder passwordEncoder, JwtUtil jwtUtil) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    public AuthResponse login(LoginRequest request) {
        User user = userService.findByEmail(request.getEmailUser())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!passwordEncoder.matches(request.getPasswordUser(), user.getPassword())) {
            throw new RuntimeException("Invalid credentials");
        }

        UserDetailDTO userDetailDTO = userService.findUserDTOById(user.getId())
                .orElseThrow(() -> new NotFoundException("User not found"));

        String token = jwtUtil.generateToken(userDetailDTO);

        return new AuthResponse(token, userDetailDTO);
    }

}
