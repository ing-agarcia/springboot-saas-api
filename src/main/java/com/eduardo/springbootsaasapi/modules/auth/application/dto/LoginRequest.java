package com.eduardo.springbootsaasapi.modules.auth.application.dto;

import lombok.Data;

@Data
public class LoginRequest {
    private String emailUser;
    private String passwordUser;
}
