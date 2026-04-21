package com.eduardo.springbootsaasapi.modules.role.application.mappers;

import com.eduardo.springbootsaasapi.modules.role.application.dto.RoleDTO;
import com.eduardo.springbootsaasapi.modules.role.domain.entities.Role;
import com.eduardo.springbootsaasapi.modules.role.infrastructure.persistence.entities.RoleEntity;

public class RoleMapper {

    public static Role toDomain(RoleEntity entity) {
        if (entity == null)
            return null;

        return new Role(
                entity.getId(),
                entity.getName());
    }

    public static RoleDTO toDTO(Role role) {
        if (role == null)
            return null;

        return new RoleDTO(
                role.getId(),
                role.getName());
    }

}
