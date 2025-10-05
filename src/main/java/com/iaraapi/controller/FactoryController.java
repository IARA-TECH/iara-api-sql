package com.iaraapi.controller;

import com.iaraapi.dto.request.FactoryRequest;
import com.iaraapi.dto.response.FactoryResponse;
import com.iaraapi.service.FactoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/factories")
@RequiredArgsConstructor
@CrossOrigin("*")
public class FactoryController {

    private final FactoryService factoryService;

    @PostMapping
    public ResponseEntity<FactoryResponse> createFactory(@RequestBody @Valid FactoryRequest factoryRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(factoryService.create(factoryRequest));
    }

    @GetMapping
    public ResponseEntity<List<FactoryResponse>> getAllFactories() {
        return ResponseEntity.ok(factoryService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<FactoryResponse> getFactory(@PathVariable Long id) {
        return ResponseEntity.ok(factoryService.getById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<FactoryResponse> updateFactory(@PathVariable Long id,
                                                         @RequestBody @Valid FactoryRequest factoryRequest) {
        return ResponseEntity.ok(factoryService.update(id, factoryRequest));
    }

    @PatchMapping("/deactivate/{id}")
    public ResponseEntity<FactoryResponse> deactivateFactory(@PathVariable Long id) {
        return ResponseEntity.ok(factoryService.deactivateEntity(id));
    }

    @PatchMapping("/reactivate/{id}")
    public ResponseEntity<FactoryResponse> reactivateFactory(@PathVariable Long id) {
        return ResponseEntity.ok(factoryService.reactivateEntity(id));
    }
}
