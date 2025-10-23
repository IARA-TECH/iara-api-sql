package com.iaraapi.controller;

import com.iaraapi.controller.contract.GenderContract;
import com.iaraapi.model.dto.request.GenderRequest;
import com.iaraapi.model.dto.response.GenderResponse;
import com.iaraapi.service.GenderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/genders")
@RequiredArgsConstructor
@CrossOrigin("*")
public class GenderController implements GenderContract {
    private final GenderService genderService;

    @PostMapping
    @PreAuthorize("hasRole('Administrador')")
    public ResponseEntity<GenderResponse> createGender(@RequestBody @Valid GenderRequest genderRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(genderService.create(genderRequest));
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('Administrador', 'Visualizador')")
    public ResponseEntity<List<GenderResponse>> getAllGender() {
        return ResponseEntity.ok(genderService.getAll());
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('Administrador', 'Visualizador')")
    public ResponseEntity<GenderResponse> getGender(@PathVariable Integer id) {
        return ResponseEntity.ok(genderService.getById(id));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('Administrador')")
    public ResponseEntity<GenderResponse> updateGender(@PathVariable Integer id, @RequestBody @Valid GenderRequest genderRequest) {
        return ResponseEntity.ok(genderService.update(id, genderRequest));
    }

    @PatchMapping("/deactivate/{id}")
    @PreAuthorize("hasRole('Administrador')")
    public ResponseEntity<GenderResponse> deactivateGender(@PathVariable Integer id) {
        return ResponseEntity.ok(genderService.deactivateEntity(id));
    }

    @PatchMapping("/reactivate/{id}")
    @PreAuthorize("hasRole('Administrador')")
    public ResponseEntity<GenderResponse> reactivateGender(@PathVariable Integer id) {
        return ResponseEntity.ok(genderService.reactivateEntity(id));
    }
}
