package com.eduardo.springbootsaasapi.modules.user.application.services;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.eduardo.springbootsaasapi.modules.user.application.dto.UserCreateDTO;
import com.eduardo.springbootsaasapi.modules.user.application.dto.UserDetailDTO;
import com.eduardo.springbootsaasapi.modules.user.application.dto.UserHierarchyDTO;
import com.eduardo.springbootsaasapi.modules.user.application.dto.UserUpdateDTO;
import com.eduardo.springbootsaasapi.modules.user.application.mappers.UserMapper;
import com.eduardo.springbootsaasapi.modules.user.domain.entities.User;
import com.eduardo.springbootsaasapi.modules.user.domain.repositories.UserRepository;
import com.eduardo.springbootsaasapi.shared.infrastructure.exceptions.BadRequestException;
import com.eduardo.springbootsaasapi.shared.infrastructure.exceptions.NotFoundException;

@Service
@Transactional
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public UserDetailDTO createUser(UserCreateDTO userCreateDTO) {

        if (userRepository.findByEmail(userCreateDTO.getEmail()).isPresent()) {
            throw new BadRequestException("Email already exists");
        }

        User user = UserMapper.toUser(userCreateDTO);
        user.setPassword(passwordEncoder.encode(userCreateDTO.getPassword()));
        User saved = userRepository.save(user);

        return userRepository.findUserDTOById(saved.getId())
                .orElseThrow(() -> new NotFoundException("User not found"));
    }

    public Page<UserDetailDTO> getAllUsers(Integer userId, Pageable pageable) {
        return userRepository.findAllUsers(userId, pageable);
    }

    public UserDetailDTO updateUser(Integer id, UserUpdateDTO userUpdateDTO) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("User not found"));

        if (userUpdateDTO.getEmail() != null && !userUpdateDTO.getEmail().equals(user.getEmail())) {
            Optional<User> existing = userRepository.findByEmail(userUpdateDTO.getEmail());
            if (existing.isPresent()) {
                throw new BadRequestException("Email already exists");
            }
            user.setEmail(userUpdateDTO.getEmail());
        }

        if (userUpdateDTO.getName() != null) {
            user.setName(userUpdateDTO.getName());
        }

        if (userUpdateDTO.getRoleId() != null) {
            user.setRoleId(userUpdateDTO.getRoleId());
        }

        if (userUpdateDTO.getGroupId() != null) {
            user.setGroupId(userUpdateDTO.getGroupId());
        }

        if (userUpdateDTO.getManagerId() != null) {
            user.setManagerId(userUpdateDTO.getManagerId());
        }
        User update = userRepository.save(user);

        return userRepository.findUserDTOById(update.getId())
                .orElseThrow(() -> new NotFoundException("User not found"));
    }

    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public void deleteUser(Integer id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("User not found"));
        userRepository.delete(user);
    }

    public Optional<UserDetailDTO> findUserDTOById(Integer id) {
        return userRepository.findUserDTOById(id);
    }

    public List<UserHierarchyDTO> getUsersByRole(Integer roleId) {
        return userRepository.getUsersByRole(roleId)
                .stream()
                .map(UserMapper::toHierarchy)
                .toList();
    }
}
