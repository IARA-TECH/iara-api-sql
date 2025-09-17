package com.iaraapi.controller;

import com.iaraapi.dto.request.SubscriptionRequest;
import com.iaraapi.dto.response.SubscriptionResponse;
import com.iaraapi.service.SubscriptionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
@CrossOrigin("*")
public class SubscriptionController {
    private final SubscriptionService subscriptionService;

    @PostMapping("/subscriptions")
    public ResponseEntity<SubscriptionResponse> createSubscription(@RequestBody @Valid SubscriptionRequest subscriptionRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(subscriptionService.createSubscription(subscriptionRequest));
    }

    @GetMapping("/subscriptions")
    public ResponseEntity<List<SubscriptionResponse>> getAllSubscriptions() {
        return ResponseEntity.ok(subscriptionService.getAllSubscriptions());
    }

    @GetMapping("/subscriptions/{id}")
    public ResponseEntity<SubscriptionResponse> getSubscription(@PathVariable Long id) {
        return ResponseEntity.ok(subscriptionService.getSubscriptionById(id));
    }

    @PutMapping("/subscriptions/{id}")
    public ResponseEntity<SubscriptionResponse> updateSubscription(@PathVariable Long id,
                                           @RequestBody @Valid SubscriptionRequest subscriptionRequest) {
        return ResponseEntity.ok(subscriptionService.updateSubscriptionById(id, subscriptionRequest));
    }

    @DeleteMapping("/subscriptions/{id}")
    public ResponseEntity<SubscriptionResponse> deleteSubscription(@PathVariable Long id) {
        return ResponseEntity.ok(subscriptionService.deleteSubscriptionById(id));
    }
}
