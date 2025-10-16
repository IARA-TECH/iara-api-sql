package com.iaraapi.controller;

import com.iaraapi.controller.contract.UserPhotoContract;
import com.iaraapi.model.dto.request.UserPhotoRequest;
import com.iaraapi.model.dto.response.UserPhotoResponse;
import com.iaraapi.service.UserPhotoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/user-photos")
@RequiredArgsConstructor
@CrossOrigin("*")
public class UserPhotoController implements UserPhotoContract {

    private final UserPhotoService userPhotoService;

    @PostMapping
    public ResponseEntity<UserPhotoResponse> createUserPhoto(@RequestBody @Valid UserPhotoRequest userPhotoRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userPhotoService.create(userPhotoRequest));
    }

    @GetMapping
    public ResponseEntity<List<UserPhotoResponse>> getAllUserPhotos() {
        return ResponseEntity.ok(userPhotoService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserPhotoResponse> getUserPhoto(@PathVariable Integer id) {
        return ResponseEntity.ok(userPhotoService.getById(id));
    }

    @GetMapping("/by-user/{userId}")
    public ResponseEntity<UserPhotoResponse> getUserPhotoByUserId(@PathVariable UUID userId) {
        return ResponseEntity.ok(userPhotoService.getPhotoByUserId(userId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserPhotoResponse> updateUserPhoto(@PathVariable Integer id, @RequestBody @Valid UserPhotoRequest userPhotoRequest) {
        return ResponseEntity.ok(userPhotoService.update(id, userPhotoRequest));
    }
}
