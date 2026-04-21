package com.eduardo.springbootsaasapi.modules.user.infrastructure.domain;

import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import com.eduardo.springbootsaasapi.modules.group.infrastructure.persistence.entities.GroupEntity;
import com.eduardo.springbootsaasapi.modules.role.infrastructure.persistence.entities.RoleEntity;
import com.eduardo.springbootsaasapi.modules.user.application.dto.UserHierarchyDTO;
import com.eduardo.springbootsaasapi.modules.user.domain.entities.User;
import com.eduardo.springbootsaasapi.modules.user.infrastructure.persistence.entities.UserEntity;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Component
public class UserEntityMapper {

    @PersistenceContext
    private EntityManager entityManager;

    @NonNull
    public UserEntity toEntity(User user) {
        UserEntity entity = new UserEntity();
        entity.setId(user.getId());
        entity.setName(user.getName());
        entity.setEmail(user.getEmail());
        entity.setPassword(user.getPassword());
        if (user.getRoleId() != null) {
            entity.setRole(entityManager.getReference(RoleEntity.class, user.getRoleId()));
        }

        if (user.getGroupId() != null) {
            entity.setGroup(entityManager.getReference(GroupEntity.class, user.getGroupId()));
        }

        if (user.getManagerId() != null) {
            entity.setManager(entityManager.getReference(UserEntity.class, user.getManagerId()));
        }

        return entity;
    }

    public User toDomain(UserEntity entity) {
        User user = new User();
        user.setId(entity.getId());
        user.setName(entity.getName());
        user.setEmail(entity.getEmail());
        user.setPassword(entity.getPassword());

        user.setRoleId(entity.getRole() != null ? entity.getRole().getId() : null);
        user.setGroupId(entity.getGroup() != null ? entity.getGroup().getId() : null);
        user.setManagerId(entity.getManager() != null ? entity.getManager().getId() : null);

        return user;
    }

    public User toHierarchy(UserHierarchyDTO userHierarchyDTO) {
        User user = new User();
        user.setId(userHierarchyDTO.getId());
        user.setName(userHierarchyDTO.getName());

        return user;
    }
}
