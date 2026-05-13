package com.eduardo.springbootsaasapi.modules.user.presentation;

import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.eduardo.springbootsaasapi.modules.user.application.dto.UserCreateDTO;
import com.eduardo.springbootsaasapi.modules.user.application.dto.UserDetailDTO;
import com.eduardo.springbootsaasapi.modules.user.application.dto.UserHierarchyDTO;
import com.eduardo.springbootsaasapi.modules.user.application.dto.UserUpdateDTO;
import com.eduardo.springbootsaasapi.modules.user.application.services.UserService;
import com.eduardo.springbootsaasapi.shared.application.dto.PaginatedResultDTO;
import com.eduardo.springbootsaasapi.shared.application.mappers.TableResponseMapper;
import com.eduardo.springbootsaasapi.shared.infrastructure.reports.JasperReportService;

import org.springframework.http.HttpHeaders;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@RestController
@RequestMapping("/api/users")

public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<UserDetailDTO> createUser(@RequestBody UserCreateDTO userCreateDTO) {
        UserDetailDTO created = userService.createUser(userCreateDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @GetMapping
    public ResponseEntity<PaginatedResultDTO<UserDetailDTO>> getAllUsers(
            @AuthenticationPrincipal(expression = "id") Integer userId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "50") int size) {

        Pageable pageable = PageRequest.of(page, size);
        Page<UserDetailDTO> users = userService.getAllUsers(userId, pageable);
        return ResponseEntity.ok(TableResponseMapper.buildResponse(users));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDetailDTO> updateUser(@PathVariable Integer id,
            @RequestBody UserUpdateDTO userUpdateDTO) {
        UserDetailDTO updated = userService.updateUser(id, userUpdateDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Integer id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

    @Autowired
    private JasperReportService jasperReportService;

    @SuppressWarnings("null")
    @GetMapping("/report")
    public ResponseEntity<byte[]> usersReport(
            @AuthenticationPrincipal(expression = "id") Integer userId) {

        List<UserDetailDTO> usersPage = userService.getUsersReport(userId);

        byte[] pdf = jasperReportService.generateReport(
                "Users",
                usersPage);

        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_PDF)
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=users-report.pdf")
                .body(pdf);
    }

    @GetMapping("/managers/{roleId}")
    public ResponseEntity<List<UserHierarchyDTO>> getManagersByRole(@PathVariable Integer roleId) {
        return ResponseEntity.ok(userService.getManagersByRole(roleId));
    }

    @GetMapping("/role/{roleId}")
    public ResponseEntity<List<UserHierarchyDTO>> getUsersByRole(@PathVariable Integer roleId) {
        return ResponseEntity.ok(userService.getUsersByRole(roleId));
    }

}
