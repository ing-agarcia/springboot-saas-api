package com.eduardo.springbootsaasapi.shared.infrastructure.exceptions;

import java.time.LocalDateTime;

public record ErrorResponse(
                int status,
                String error,
                String message,
                String path,
                LocalDateTime timestamp) {

}
