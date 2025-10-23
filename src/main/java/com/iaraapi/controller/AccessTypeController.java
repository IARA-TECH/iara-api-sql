package com.iaraapi.controller;

import com.iaraapi.controller.contract.AccessTypeContract;
import com.iaraapi.model.dto.request.AccessTypeRequest;
import com.iaraapi.model.dto.response.AccessTypeResponse;
import com.iaraapi.service.AccessTypeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/access-types")
@RequiredArgsConstructor
@CrossOrigin("*")
public class AccessTypeController implements AccessTypeContract {
    private final AccessTypeService accessTypeService;

    @PostMapping
    @PreAuthorize("hasRole('Administrador')")
    public ResponseEntity<AccessTypeResponse> createAccessType(@RequestBody @Valid AccessTypeRequest accessTypeRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(accessTypeService.create(accessTypeRequest));
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('Administrador', 'Visualizador')")
    public ResponseEntity<List<AccessTypeResponse>> getAllAccessTypes() {
        return ResponseEntity.ok(accessTypeService.getAll());
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('Administrador', 'Visualizador')")
    public ResponseEntity<AccessTypeResponse> getAccessType(@PathVariable Integer id) {
        return ResponseEntity.ok(accessTypeService.getById(id));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('Administrador')")
    public ResponseEntity<AccessTypeResponse> updateAccessType(@PathVariable Integer id, @RequestBody @Valid AccessTypeRequest accessTypeRequest) {
        return ResponseEntity.ok(accessTypeService.update(id, accessTypeRequest));
    }

    @PatchMapping("/deactivate/{id}")
    @PreAuthorize("hasRole('Administrador')")
    public ResponseEntity<AccessTypeResponse> deactivateAccessType(@PathVariable Integer id) {
        return ResponseEntity.ok(accessTypeService.deactivateEntity(id));
    }

    @PatchMapping("/reactivate/{id}")
    @PreAuthorize("hasRole('Administrador')")
    public ResponseEntity<AccessTypeResponse> reactivateAccessType(@PathVariable Integer id) {
        return ResponseEntity.ok(accessTypeService.reactivateEntity(id));
    }
}
