package com.eduardo.springbootsaasapi.modules.auth.presentation;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.eduardo.springbootsaasapi.modules.auth.application.dto.AuthResponse;
import com.eduardo.springbootsaasapi.modules.auth.application.dto.LoginRequest;
import com.eduardo.springbootsaasapi.modules.auth.application.service.AuthService;
import com.eduardo.springbootsaasapi.shared.infrastructure.config.JwtUtil;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;
    private final JwtUtil jwtUtil;

    public AuthController(AuthService authService, JwtUtil jwtUtil) {
        this.authService = authService;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request) {
        return ResponseEntity.ok(authService.login(request));
    }

    @GetMapping("/validate")
    public ResponseEntity<?> validate(@RequestHeader("Authorization") String authHeader) {

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return ResponseEntity.badRequest().body(Map.of("valid", false));
        }

        String token = authHeader.substring(7);

        boolean valid = jwtUtil.validateToken(token);

        return ResponseEntity.ok(Map.of("valid", valid));
    }

}
