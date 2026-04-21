package com.eduardo.springbootsaasapi.modules.group.application.mappers;

import com.eduardo.springbootsaasapi.modules.group.application.dto.GroupDTO;
import com.eduardo.springbootsaasapi.modules.group.domain.entities.Group;
import com.eduardo.springbootsaasapi.modules.group.infrastructure.persistence.entities.GroupEntity;

public class GroupMapper {

    public static Group toDomain(GroupEntity groupEntity) {
        if (groupEntity == null)
            return null;

        return new Group(
                groupEntity.getId(),
                groupEntity.getName());
    }

    public static GroupDTO toDto(Group group) {
        if (group == null)
            return null;

        return new GroupDTO(
                group.getId(),
                group.getName());

    }

}
