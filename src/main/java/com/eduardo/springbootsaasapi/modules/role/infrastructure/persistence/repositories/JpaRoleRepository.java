package com.eduardo.springbootsaasapi.modules.role.infrastructure.persistence.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eduardo.springbootsaasapi.modules.role.infrastructure.persistence.entities.RoleEntity;

public interface JpaRoleRepository extends JpaRepository<RoleEntity, Integer> {

}
