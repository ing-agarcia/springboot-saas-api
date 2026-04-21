package com.eduardo.springbootsaasapi.modules.user.application.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class UserHierarchyDTO {
    private Integer id;
    private String name;
}
