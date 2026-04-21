package com.eduardo.springbootsaasapi.shared.infrastructure.exceptions;

public class NotFoundException extends BaseException {
    public NotFoundException(String message) {
        super(message, 404);
    }
}
