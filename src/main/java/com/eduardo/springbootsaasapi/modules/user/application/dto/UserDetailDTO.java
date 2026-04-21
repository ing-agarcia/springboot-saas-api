package com.eduardo.springbootsaasapi.modules.user.application.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class UserDetailDTO {
    private Integer id;
    private String name;
    private String email;
    private String role;
    private Integer roleId;
    private String group;
    private Integer groupId;
    private String manager;
    private Integer managerId;
    private LocalDateTime createdAt;
}
