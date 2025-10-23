package com.iaraapi.controller;

import com.iaraapi.controller.contract.SubscriptionContract;
import com.iaraapi.model.dto.request.SubscriptionRequest;
import com.iaraapi.model.dto.response.SubscriptionResponse;
import com.iaraapi.service.SubscriptionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/subscriptions")
@RequiredArgsConstructor
@CrossOrigin("*")
public class SubscriptionController implements SubscriptionContract {

    private final SubscriptionService subscriptionService;

    @PostMapping
    @PreAuthorize("hasRole('Administrador')")
    public ResponseEntity<SubscriptionResponse> createSubscription(
            @RequestBody @Valid SubscriptionRequest subscriptionRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(subscriptionService.create(subscriptionRequest));
    }

    @GetMapping
    @PreAuthorize("hasRole('Administrador')")
    public ResponseEntity<List<SubscriptionResponse>> getAllSubscriptions() {
        return ResponseEntity.ok(subscriptionService.getAll());
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('Administrador')")
    public ResponseEntity<SubscriptionResponse> getSubscription(@PathVariable Integer id) {
        return ResponseEntity.ok(subscriptionService.getById(id));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('Administrador')")
    public ResponseEntity<SubscriptionResponse> updateSubscription(
            @PathVariable Integer id,
            @RequestBody @Valid SubscriptionRequest subscriptionRequest) {
        return ResponseEntity.ok(subscriptionService.update(id, subscriptionRequest));
    }

    @PatchMapping("/deactivate/{id}")
    @PreAuthorize("hasRole('Administrador')")
    public ResponseEntity<SubscriptionResponse> deactivateSubscription(@PathVariable Integer id) {
        return ResponseEntity.ok(subscriptionService.deactivateEntity(id));
    }

    @PatchMapping("/reactivate/{id}")
    @PreAuthorize("hasRole('Administrador')")
    public ResponseEntity<SubscriptionResponse> reactivateSubscription(@PathVariable Integer id) {
        return ResponseEntity.ok(subscriptionService.reactivateEntity(id));
    }
}
