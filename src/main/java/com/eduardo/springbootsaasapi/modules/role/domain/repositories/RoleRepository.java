package com.eduardo.springbootsaasapi.modules.role.domain.repositories;

import java.util.List;
import java.util.Optional;

import com.eduardo.springbootsaasapi.modules.role.domain.entities.Role;

public interface RoleRepository {
    Optional<Role> findById(Integer id);

    List<Role> findAll();
}
