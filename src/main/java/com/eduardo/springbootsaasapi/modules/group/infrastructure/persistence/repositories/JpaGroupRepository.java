package com.eduardo.springbootsaasapi.modules.group.infrastructure.persistence.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eduardo.springbootsaasapi.modules.group.infrastructure.persistence.entities.GroupEntity;

public interface JpaGroupRepository extends JpaRepository<GroupEntity, Integer> {

}
