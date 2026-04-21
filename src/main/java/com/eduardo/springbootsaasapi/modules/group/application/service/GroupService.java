package com.eduardo.springbootsaasapi.modules.group.application.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.eduardo.springbootsaasapi.modules.group.application.dto.GroupDTO;
import com.eduardo.springbootsaasapi.modules.group.application.mappers.GroupMapper;
import com.eduardo.springbootsaasapi.modules.group.domain.repositories.GroupRepository;

@Service
public class GroupService {

    private final GroupRepository groupRepository;

    public GroupService(GroupRepository groupRepository) {
        this.groupRepository = groupRepository;
    }

    public List<GroupDTO> getGroups() {
        return groupRepository.findGroups()
                .stream()
                .map(GroupMapper::toDto)
                .toList();
    }

}
