package com.eduardo.springbootsaasapi.shared.infrastructure.config;

public class JwtUserPrincipal {

    private final Integer id;
    private final String email;
    private final String role;

    public JwtUserPrincipal(Integer id, String email, String role) {
        this.id = id;
        this.email = email;
        this.role = role;
    }

    public Integer getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public boolean hasRole(String role) {
        return this.role.equalsIgnoreCase(role);
    }

}
