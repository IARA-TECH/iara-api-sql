package com.iaraapi.controller;

import com.iaraapi.controller.contract.PaymentMethodContract;
import com.iaraapi.model.dto.request.PaymentMethodRequest;
import com.iaraapi.model.dto.response.PaymentMethodResponse;
import com.iaraapi.service.PaymentMethodService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/payment-methods")
@RequiredArgsConstructor
@CrossOrigin("*")
public class PaymentMethodController implements PaymentMethodContract {
    private final PaymentMethodService paymentMethodService;

    @PostMapping
    @PreAuthorize("hasRole('Administrador')")
    public ResponseEntity<PaymentMethodResponse> createPaymentMethod(@RequestBody @Valid PaymentMethodRequest paymentMethodRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(paymentMethodService.create(paymentMethodRequest));
    }

    @GetMapping
    @PreAuthorize("hasRole('Administrador')")
    public ResponseEntity<List<PaymentMethodResponse>> getAllPaymentMethods() {
        return ResponseEntity.ok(paymentMethodService.getAll());
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('Administrador')")
    public ResponseEntity<PaymentMethodResponse> getPaymentMethod(@PathVariable Integer id) {
        return ResponseEntity.ok(paymentMethodService.getById(id));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('Administrador')")
    public ResponseEntity<PaymentMethodResponse> updatePaymentMethod(@PathVariable Integer id,
                                                                     @RequestBody @Valid PaymentMethodRequest paymentMethodRequest) {
        return ResponseEntity.ok(paymentMethodService.update(id, paymentMethodRequest));
    }

    @PatchMapping("/deactivate/{id}")
    @PreAuthorize("hasRole('Administrador')")
    public ResponseEntity<PaymentMethodResponse> deactivatePaymentMethod(@PathVariable Integer id) {
        return ResponseEntity.ok(paymentMethodService.deactivateEntity(id));
    }

    @PatchMapping("/reactivate/{id}")
    @PreAuthorize("hasRole('Administrador')")
    public ResponseEntity<PaymentMethodResponse> reactivatePaymentMethod(@PathVariable Integer id) {
        return ResponseEntity.ok(paymentMethodService.reactivateEntity(id));
    }
}
