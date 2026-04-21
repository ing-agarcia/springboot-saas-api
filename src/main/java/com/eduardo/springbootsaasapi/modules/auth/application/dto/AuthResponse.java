package com.eduardo.springbootsaasapi.modules.auth.application.dto;

import com.eduardo.springbootsaasapi.modules.user.application.dto.UserDetailDTO;

public record AuthResponse(
        String token,
        UserDetailDTO user) {
}
