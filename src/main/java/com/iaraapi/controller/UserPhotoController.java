package com.iaraapi.controller;

import com.iaraapi.controller.contract.UserPhotoContract;
import com.iaraapi.model.dto.request.UserPhotoRequest;
import com.iaraapi.model.dto.response.UserPhotoResponse;
import com.iaraapi.service.UserPhotoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("api/v1/user-photos")
@RequiredArgsConstructor
@CrossOrigin("*")
public class UserPhotoController implements UserPhotoContract {

    private final UserPhotoService userPhotoService;

    @PostMapping
    @PreAuthorize("hasAnyRole('Administrador', 'Solicitante', 'Supervisor', 'Visualizador')")
    public ResponseEntity<UserPhotoResponse> createUserPhoto(@RequestBody @Valid UserPhotoRequest userPhotoRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userPhotoService.create(userPhotoRequest));
    }

    @GetMapping("/by-user/{userId}")
    @PreAuthorize("hasAnyRole('Administrador', 'Solicitante', 'Supervisor', 'Visualizador')")
    public ResponseEntity<UserPhotoResponse> getUserPhotoByUserId(@PathVariable UUID userId) {
        return ResponseEntity.ok(userPhotoService.getPhotoByUserId(userId));
    }

    @PutMapping("/by-user/{userId}")
    @PreAuthorize("hasAnyRole('Administrador', 'Solicitante', 'Supervisor', 'Visualizador')")
    public ResponseEntity<UserPhotoResponse> updateUserPhoto(@PathVariable UUID userId, @RequestBody @Valid UserPhotoRequest userPhotoRequest) {
        return ResponseEntity.ok(userPhotoService.updateByUserId(userId, userPhotoRequest));
    }
}
