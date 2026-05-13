package com.eduardo.springbootsaasapi.modules.user.infrastructure.persistence.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.eduardo.springbootsaasapi.modules.user.application.dto.UserDetailDTO;
import com.eduardo.springbootsaasapi.modules.user.application.dto.UserHierarchyDTO;
import com.eduardo.springbootsaasapi.modules.user.infrastructure.persistence.entities.UserEntity;

import java.util.List;
import java.util.Optional;

public interface JpaUserRepository extends JpaRepository<UserEntity, Integer> {

    @Query(value = """
                SELECT new com.eduardo.springbootsaasapi.modules.user.application.dto.UserDetailDTO(
                    u.id,
                    u.name,
                    u.email,
                    r.name,
                    r.id,
                    g.name,
                    g.id,
                    m.name,
                    m.id,
                    u.createdAt
                )
                FROM UserHierarchy uh
                    JOIN UserEntity u ON u.id = uh.childUserId
                    LEFT JOIN u.role r
                    LEFT JOIN u.group g
                    LEFT JOIN u.manager m
                WHERE uh.parentUserId = :userId
                ORDER BY u.role.id
            """, countQuery = """
                    SELECT
                        COUNT(u)
                    FROM
                        UserHierarchy uh JOIN UserEntity u ON u.id = uh.childUserId
                    WHERE
                        uh.parentUserId = :userId
            """)
    Page<UserDetailDTO> findAllUsers(Integer userId, Pageable pageable);

    @Query(value = """
                SELECT u
                    FROM UserEntity u
                    WHERE u.email = :email
            """)
    Optional<UserEntity> findByEmail(@Param("email") String email);

    @Query(value = """
                SELECT new com.eduardo.springbootsaasapi.modules.user.application.dto.UserDetailDTO(
                    u.id,
                    u.name,
                    u.email,
                    r.name,
                    r.id,
                    g.name,
                    g.id,
                    m.name,
                    m.id,
                    u.createdAt
                )
                FROM UserEntity u
                    LEFT JOIN u.role r
                    LEFT JOIN u.group g
                    LEFT JOIN u.manager m
                WHERE
                    u.id = :id
            """)
    Optional<UserDetailDTO> findUserDTOById(@Param("id") Integer id);

    @Query(value = """
                SELECT new com.eduardo.springbootsaasapi.modules.user.application.dto.UserHierarchyDTO(u.id, u.name)
                FROM UserEntity u
                JOIN RoleHierarchyEntity rh ON u.role.id = rh.parentRole.id
                WHERE rh.role.id = :roleId
            """)
    List<UserHierarchyDTO> getManagersByRole(@Param("roleId") Integer roleId);

    @Query(value = """
                SELECT new com.eduardo.springbootsaasapi.modules.user.application.dto.UserDetailDTO(
                    u.id,
                    u.name,
                    u.email,
                    r.name,
                    r.id,
                    g.name,
                    g.id,
                    m.name,
                    m.id,
                    u.createdAt
                )
                FROM UserHierarchy uh
                    JOIN UserEntity u ON u.id = uh.childUserId
                    LEFT JOIN u.role r
                    LEFT JOIN u.group g
                    LEFT JOIN u.manager m
                WHERE uh.parentUserId = :userId
                ORDER BY u.role.id
            """)
    List<UserDetailDTO> findUsersReport(Integer userId);

    @Query(value = """
                SELECT u
                FROM UserEntity u
                WHERE u.role.id = :roleId
            """)
    List<UserEntity> getUsersByRole(@Param("roleId") Integer roleId);
}
