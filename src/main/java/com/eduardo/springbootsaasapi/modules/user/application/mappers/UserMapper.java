package com.eduardo.springbootsaasapi.modules.user.application.mappers;

import com.eduardo.springbootsaasapi.modules.user.application.dto.UserCreateDTO;
import com.eduardo.springbootsaasapi.modules.user.application.dto.UserHierarchyDTO;
import com.eduardo.springbootsaasapi.modules.user.domain.entities.User;

public class UserMapper {

    public static User toUser(UserCreateDTO userCreatedDTO) {
        User user = new User();
        user.setName(userCreatedDTO.getName());
        user.setEmail(userCreatedDTO.getEmail());
        user.setRoleId(userCreatedDTO.getRoleId());
        user.setGroupId(userCreatedDTO.getGroupId());
        user.setManagerId(userCreatedDTO.getManagerId());
        return user;
    }

    public static UserHierarchyDTO toHierarchy(User user) {
        return new UserHierarchyDTO(
                user.getId(),
                user.getName());

    }

}
