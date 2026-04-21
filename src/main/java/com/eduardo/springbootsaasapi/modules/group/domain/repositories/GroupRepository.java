package com.eduardo.springbootsaasapi.modules.group.domain.repositories;

import java.util.List;
import java.util.Optional;

import com.eduardo.springbootsaasapi.modules.group.domain.entities.Group;

public interface GroupRepository {
    Optional<Group> findById(Integer id);

    List<Group> findGroups();
}
