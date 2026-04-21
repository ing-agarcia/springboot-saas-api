package com.eduardo.springbootsaasapi.modules.user.infrastructure.persistence.entities;

import jakarta.persistence.*;

import java.time.LocalDateTime;

import com.eduardo.springbootsaasapi.modules.group.infrastructure.persistence.entities.GroupEntity;
import com.eduardo.springbootsaasapi.modules.role.infrastructure.persistence.entities.RoleEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.*;

@Entity
@Table(name = "users")
@Data()
@NoArgsConstructor
@AllArgsConstructor

public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String name;
    @Column(nullable = false, unique = true)
    private String email;
    @Column(nullable = false)
    private String password;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "role_id", nullable = false)
    private RoleEntity role;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "group_id", nullable = false)
    private GroupEntity group;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "manager_id")
    @JsonIgnore
    private UserEntity manager;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

}
