package com.iaraapi.controller;

import com.iaraapi.model.dto.request.UserPhotoRequest;
import com.iaraapi.model.dto.response.UserPhotoResponse;
import com.iaraapi.service.UserPhotoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/user-photos")
@RequiredArgsConstructor
@CrossOrigin("*")
public class UserPhotoController {

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

    @PutMapping("/{id}")
    public ResponseEntity<UserPhotoResponse> updateUserPhoto(@PathVariable Integer id, @RequestBody @Valid UserPhotoRequest userPhotoRequest) {
        return ResponseEntity.ok(userPhotoService.update(id, userPhotoRequest));
    }
}
