package com.iaraapi.controller;

import com.iaraapi.controller.contract.PaymentMethodContract;
import com.iaraapi.dto.request.PaymentMethodRequest;
import com.iaraapi.dto.response.PaymentMethodResponse;
import com.iaraapi.model.PaymentMethod;
import com.iaraapi.service.PaymentMethodService;
import com.iaraapi.service.SubscriptionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/payment-methods")
@RequiredArgsConstructor
@CrossOrigin("*")
public class PaymentMethodController implements PaymentMethodContract {
    private final PaymentMethodService paymentMethodService;

    @PostMapping
    public ResponseEntity<PaymentMethodResponse> createPaymentMethod(@RequestBody @Valid PaymentMethodRequest paymentMethodRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(paymentMethodService.create(paymentMethodRequest));
    }

    @GetMapping
    public ResponseEntity<List<PaymentMethodResponse>> getAllPaymentMethods() {
        return ResponseEntity.ok(paymentMethodService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PaymentMethodResponse> getPaymentMethod(@PathVariable Integer id) {
        return ResponseEntity.ok(paymentMethodService.getById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PaymentMethodResponse> updatePaymentMethod(@PathVariable Integer id,
                                                                     @RequestBody @Valid PaymentMethodRequest paymentMethodRequest) {
        return ResponseEntity.ok(paymentMethodService.update(id, paymentMethodRequest));
    }

    @PatchMapping("/deactivate/{id}")
    public ResponseEntity<PaymentMethodResponse> deactivatePaymentMethod(@PathVariable Integer id) {
        return ResponseEntity.ok(paymentMethodService.deactivateEntity(id));
    }

    @PatchMapping("/reactivate/{id}")
    public ResponseEntity<PaymentMethodResponse> reactivatePaymentMethod(@PathVariable Integer id) {
        return ResponseEntity.ok(paymentMethodService.reactivateEntity(id));
    }
}
