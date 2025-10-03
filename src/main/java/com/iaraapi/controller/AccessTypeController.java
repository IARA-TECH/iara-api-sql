package com.iaraapi.controller;

import com.iaraapi.dto.request.AccessTypeRequest;
import com.iaraapi.dto.response.AccessTypeResponse;
import com.iaraapi.service.AccessTypeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/access-types")
@RequiredArgsConstructor
@CrossOrigin("*")
public class AccessTypeController {
    private final AccessTypeService accessTypeService;

    @PostMapping
    public ResponseEntity<AccessTypeResponse> createAccessType(@RequestBody @Valid AccessTypeRequest accessTypeRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(accessTypeService.create(accessTypeRequest));
    }

    @GetMapping
    public ResponseEntity<List<AccessTypeResponse>> getAllAccessTypes() {
        return ResponseEntity.ok(accessTypeService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AccessTypeResponse> getAccessType(@PathVariable Long id) {
        return ResponseEntity.ok(accessTypeService.getById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AccessTypeResponse> updateAccessType(@PathVariable Long id, @RequestBody @Valid AccessTypeRequest accessTypeRequest) {
        return ResponseEntity.ok(accessTypeService.update(id, accessTypeRequest));
    }

    @PatchMapping("/deactivate/{id}")
    public ResponseEntity<AccessTypeResponse> deactivateAccessType(@PathVariable Long id) {
        return ResponseEntity.ok(accessTypeService.deactivateEntity(id));
    }

    @PatchMapping("/reactivate/{id}")
    public ResponseEntity<AccessTypeResponse> reactivateAccessType(@PathVariable Long id) {
        return ResponseEntity.ok(accessTypeService.reactivateEntity(id));
    }
}
