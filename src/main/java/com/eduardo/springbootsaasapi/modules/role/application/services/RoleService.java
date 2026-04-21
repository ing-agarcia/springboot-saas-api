package com.eduardo.springbootsaasapi.modules.role.application.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.eduardo.springbootsaasapi.modules.role.application.dto.RoleDTO;
import com.eduardo.springbootsaasapi.modules.role.application.mappers.RoleMapper;
import com.eduardo.springbootsaasapi.modules.role.domain.repositories.RoleRepository;

@Service
public class RoleService {

    private final RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public List<RoleDTO> getRoles() {
        return roleRepository.findAll()
                .stream()
                .map(RoleMapper::toDTO)
                .toList();
    }

}
