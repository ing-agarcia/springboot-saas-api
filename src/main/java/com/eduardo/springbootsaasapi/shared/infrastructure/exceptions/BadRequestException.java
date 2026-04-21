package com.eduardo.springbootsaasapi.shared.infrastructure.exceptions;

public class BadRequestException extends BaseException {
    public BadRequestException(String message) {
        super(message, 400);
    }
}
