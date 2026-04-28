package com.eduardo.springbootsaasapi.modules.user.domain.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.eduardo.springbootsaasapi.modules.user.application.dto.UserDetailDTO;
import com.eduardo.springbootsaasapi.modules.user.domain.entities.User;

public interface UserRepository {

    Optional<User> findByEmail(String email);

    Optional<User> findById(Integer id);

    User save(User user);

    User update(User user);

    void delete(User user);

    Page<UserDetailDTO> findAllUsers(Integer userId, Pageable pageable);

    Optional<UserDetailDTO> findUserDTOById(Integer id);

    List<User> getUsersByRole(Integer roleId);

    List<UserDetailDTO> findUsersReport(Integer userId);
}
