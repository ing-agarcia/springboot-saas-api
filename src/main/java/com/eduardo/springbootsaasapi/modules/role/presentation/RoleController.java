package com.eduardo.springbootsaasapi.modules.role.presentation;

import java.util.List;

import org.springframework.web.bind.annotation.RestController;

import com.eduardo.springbootsaasapi.modules.role.application.dto.RoleDTO;
import com.eduardo.springbootsaasapi.modules.role.application.services.RoleService;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/api/roles")

public class RoleController {

    private final RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @GetMapping
    public ResponseEntity<List<RoleDTO>> getRoles() {
        return ResponseEntity.ok(roleService.getRoles());
    }

}
