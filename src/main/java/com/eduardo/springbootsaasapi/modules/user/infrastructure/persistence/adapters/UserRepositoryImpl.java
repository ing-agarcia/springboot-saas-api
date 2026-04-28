package com.eduardo.springbootsaasapi.modules.user.infrastructure.persistence.adapters;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.eduardo.springbootsaasapi.modules.user.application.dto.UserDetailDTO;
import com.eduardo.springbootsaasapi.modules.user.domain.entities.User;
import com.eduardo.springbootsaasapi.modules.user.domain.repositories.UserRepository;
import com.eduardo.springbootsaasapi.modules.user.infrastructure.domain.UserEntityMapper;
import com.eduardo.springbootsaasapi.modules.user.infrastructure.persistence.entities.UserEntity;
import com.eduardo.springbootsaasapi.modules.user.infrastructure.persistence.repositories.JpaUserRepository;

@Repository

public class UserRepositoryImpl implements UserRepository {

    private final JpaUserRepository jpaUserRepository;
    private final UserEntityMapper userEntityMapper;

    public UserRepositoryImpl(JpaUserRepository jpaUserRepository, UserEntityMapper userEntityMapper) {
        this.jpaUserRepository = jpaUserRepository;
        this.userEntityMapper = userEntityMapper;
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return jpaUserRepository.findByEmail(email)
                .map(userEntityMapper::toDomain);
    }

    @Override
    @SuppressWarnings("null")
    public Optional<User> findById(Integer id) {
        return jpaUserRepository.findById(id)
                .map(userEntityMapper::toDomain);
    }

    @Override
    public User save(User user) {
        UserEntity entity = userEntityMapper.toEntity(user);
        UserEntity saved = jpaUserRepository.save(entity);
        return userEntityMapper.toDomain(saved);
    }

    @Override
    public User update(User user) {
        UserEntity entity = userEntityMapper.toEntity(user);
        UserEntity saved = jpaUserRepository.save(entity);
        return userEntityMapper.toDomain(saved);
    }

    @Override
    public void delete(User user) {
        UserEntity entity = userEntityMapper.toEntity(user);
        jpaUserRepository.delete(entity);
    }

    @Override
    public Page<UserDetailDTO> findAllUsers(Integer userId, Pageable pageable) {
        return jpaUserRepository.findAllUsers(userId, pageable);
    }

    @Override
    public Optional<UserDetailDTO> findUserDTOById(Integer id) {
        return jpaUserRepository.findUserDTOById(id);
    }

    @Override
    public List<User> getUsersByRole(Integer roleId) {
        return jpaUserRepository.getUsersByRole(roleId)
                .stream()
                .map(userEntityMapper::toHierarchy)
                .toList();
    }

    @Override
    public List<UserDetailDTO> findUsersReport(Integer userId) {
        return jpaUserRepository.findUsersReport(userId);
    }
}
