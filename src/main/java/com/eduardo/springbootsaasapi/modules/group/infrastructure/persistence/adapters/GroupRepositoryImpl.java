package com.eduardo.springbootsaasapi.modules.group.infrastructure.persistence.adapters;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Repository;

import com.eduardo.springbootsaasapi.modules.group.application.mappers.GroupMapper;
import com.eduardo.springbootsaasapi.modules.group.domain.entities.Group;
import com.eduardo.springbootsaasapi.modules.group.domain.repositories.GroupRepository;
import com.eduardo.springbootsaasapi.modules.group.infrastructure.persistence.repositories.JpaGroupRepository;

import lombok.NonNull;

@Repository
public class GroupRepositoryImpl implements GroupRepository {

    private final JpaGroupRepository jpaGroupRepository;

    public GroupRepositoryImpl(JpaGroupRepository jpaGroupRepository) {
        this.jpaGroupRepository = jpaGroupRepository;
    }

    @Override
    public Optional<Group> findById(@NonNull Integer id) {
        return jpaGroupRepository.findById(id)
                .map(GroupMapper::toDomain);
    }

    @Override
    public List<Group> findGroups() {
        return jpaGroupRepository.findAll()
                .stream()
                .map(GroupMapper::toDomain)
                .toList();
    }

}
