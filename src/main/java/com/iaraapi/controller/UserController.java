package com.iaraapi.controller;

import com.iaraapi.controller.contract.UserContract;
import com.iaraapi.model.dto.request.EmailRequest;
import com.iaraapi.model.dto.request.NameRequest;
import com.iaraapi.model.dto.request.UserRequest;
import com.iaraapi.model.dto.response.UserResponse;
import com.iaraapi.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/users")
@RequiredArgsConstructor
@CrossOrigin("*")
public class UserController implements UserContract {

    private final UserService userService;

    @PostMapping
    @PreAuthorize("hasRole('Administrador')")
    public ResponseEntity<UserResponse> createUser(@RequestBody @Valid UserRequest userRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.create(userRequest));
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('Administrador', 'Supervisor', 'Visualizador')")
    public ResponseEntity<List<UserResponse>> getAllUsers() {
        return ResponseEntity.ok(userService.getAll());
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('Administrador', 'Supervisor', 'Visualizador')")
    public ResponseEntity<UserResponse> getUserById(@PathVariable UUID id) {
        return ResponseEntity.ok(userService.getById(id));
    }

    @PostMapping("/by-email")
    @PreAuthorize("hasAnyRole('Administrador', 'Supervisor', 'Visualizador')")
    public ResponseEntity<UserResponse> getUserByEmail(@RequestBody @Valid EmailRequest request) {
        return ResponseEntity.ok(userService.getUserByEmail(request.getEmail()));
    }

    @PostMapping("/by-name")
    @PreAuthorize("hasAnyRole('Administrador', 'Supervisor', 'Visualizador')")
    public ResponseEntity<List<UserResponse>> getUserByName(@RequestBody @Valid NameRequest request) {
        return ResponseEntity.ok(userService.getUserByName(request.getName()));
    }

    @GetMapping("by-factory/{factoryId}")
    @PreAuthorize("hasAnyRole('Administrador', 'Supervisor', 'Visualizador')")
    public ResponseEntity<List<UserResponse>> getUsersByFactory(@PathVariable Integer factoryId) {
        return ResponseEntity.ok(userService.getUsersByFactory(factoryId));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('Administrador')")
    public ResponseEntity<UserResponse> updateUser(@PathVariable UUID id, @RequestBody @Valid UserRequest userRequest) {
        return ResponseEntity.ok(userService.update(id, userRequest));
    }

    @PatchMapping("/deactivate/{id}")
    @PreAuthorize("hasRole('Administrador')")
    public ResponseEntity<UserResponse> deactivateUser(@PathVariable UUID id) {
        return ResponseEntity.ok(userService.deactivateEntity(id));
    }

    @PatchMapping("/reactivate/{id}")
    @PreAuthorize("hasRole('Administrador')")
    public ResponseEntity<UserResponse> reactivateUser(@PathVariable UUID id) {
        return ResponseEntity.ok(userService.reactivateEntity(id));
    }
}
