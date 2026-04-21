package com.eduardo.springbootsaasapi.modules.user.application.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class UserCreateDTO {
    private String name;
    private String email;
    private String password;
    private Integer roleId;
    private Integer groupId;
    private Integer managerId;
}
