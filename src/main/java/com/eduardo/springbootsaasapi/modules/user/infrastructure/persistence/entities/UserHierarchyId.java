package com.eduardo.springbootsaasapi.modules.user.infrastructure.persistence.entities;

import java.io.Serializable;

import lombok.Data;

@Data

public class UserHierarchyId implements Serializable {

    private Long parentUserId;
    private Long childUserId;
}
