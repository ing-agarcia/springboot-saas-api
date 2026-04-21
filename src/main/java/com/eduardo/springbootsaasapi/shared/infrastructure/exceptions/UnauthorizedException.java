package com.eduardo.springbootsaasapi.shared.infrastructure.exceptions;

public class UnauthorizedException extends BaseException {
    public UnauthorizedException(String message) {
        super(message, 401);
    }
}
