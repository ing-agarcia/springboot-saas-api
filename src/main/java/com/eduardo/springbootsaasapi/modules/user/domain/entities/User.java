package com.eduardo.springbootsaasapi.modules.user.domain.entities;

import java.time.LocalDateTime;

import lombok.*;

@Data()
@NoArgsConstructor
@AllArgsConstructor

public class User {

    private Integer id;
    private String name;
    private String email;
    private String password;

    private Integer roleId;
    private Integer groupId;
    private Integer managerId;

    private LocalDateTime createdAt;

}
