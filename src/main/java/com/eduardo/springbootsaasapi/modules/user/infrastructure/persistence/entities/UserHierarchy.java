package com.eduardo.springbootsaasapi.modules.user.infrastructure.persistence.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "user_hierarchy")
@Data()
@NoArgsConstructor
@AllArgsConstructor
@IdClass(UserHierarchyId.class)
public class UserHierarchy {

    @Id
    @Column(name = "parent_user_id")
    private Long parentUserId;

    @Id
    @Column(name = "child_user_id")
    private Long childUserId;

    @Column(name = "level")
    private Integer level;
}
