package com.eduardo.springbootsaasapi.modules.group.application.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class GroupDTO {
    private Integer id;
    private String name;
}
