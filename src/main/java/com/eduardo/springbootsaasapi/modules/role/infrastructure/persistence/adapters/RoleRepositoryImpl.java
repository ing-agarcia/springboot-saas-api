package com.eduardo.springbootsaasapi.modules.role.infrastructure.persistence.adapters;

import lombok.NonNull;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Repository;

import com.eduardo.springbootsaasapi.modules.role.application.mappers.RoleMapper;
import com.eduardo.springbootsaasapi.modules.role.domain.entities.Role;
import com.eduardo.springbootsaasapi.modules.role.domain.repositories.RoleRepository;
import com.eduardo.springbootsaasapi.modules.role.infrastructure.persistence.repositories.JpaRoleRepository;

@Repository
public class RoleRepositoryImpl implements RoleRepository {

    private final JpaRoleRepository jpaRoleRepository;

    public RoleRepositoryImpl(JpaRoleRepository jpaRoleRepository) {
        this.jpaRoleRepository = jpaRoleRepository;
    }

    @Override
    public Optional<Role> findById(@NonNull Integer id) {
        return jpaRoleRepository.findById(id)
                .map(RoleMapper::toDomain);
    }

    @Override
    public List<Role> findAll() {
        return jpaRoleRepository.findAll()
                .stream()
                .map(RoleMapper::toDomain)
                .toList();
    }

}
