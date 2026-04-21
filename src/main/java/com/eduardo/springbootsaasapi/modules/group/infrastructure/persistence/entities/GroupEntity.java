package com.eduardo.springbootsaasapi.modules.group.infrastructure.persistence.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "groups")
@Data()
@NoArgsConstructor
@AllArgsConstructor

public class GroupEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;

}
