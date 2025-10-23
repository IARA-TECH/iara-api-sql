package com.iaraapi.controller;

import com.iaraapi.controller.contract.PaymentContract;
import com.iaraapi.model.dto.request.PaymentRequest;
import com.iaraapi.model.dto.response.PaymentResponse;
import com.iaraapi.service.PaymentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/payments")
@RequiredArgsConstructor
@CrossOrigin("*")
public class PaymentController implements PaymentContract {

    private final PaymentService paymentService;

    @PostMapping
    @PreAuthorize("hasRole('Administrador')")
    public ResponseEntity<PaymentResponse> createPayment(@RequestBody @Valid PaymentRequest paymentRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(paymentService.create(paymentRequest));
    }

    @GetMapping
    @PreAuthorize("hasRole('Administrador')")
    public ResponseEntity<List<PaymentResponse>> getAllPayments() {
        return ResponseEntity.ok(paymentService.getAll());
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('Administrador')")
    public ResponseEntity<PaymentResponse> getPaymentById(@PathVariable Integer id) {
        return ResponseEntity.ok(paymentService.getById(id));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('Administrador')")
    public ResponseEntity<PaymentResponse> updatePayment(@PathVariable Integer id, @RequestBody @Valid PaymentRequest paymentRequest) {
        return ResponseEntity.ok(paymentService.update(id, paymentRequest));
    }

    @PatchMapping("/deactivate/{id}")
    @PreAuthorize("hasRole('Administrador')")
    public ResponseEntity<PaymentResponse> deactivatePayment(@PathVariable Integer id) {
        return ResponseEntity.ok(paymentService.deactivateEntity(id));
    }

    @PatchMapping("/reactivate/{id}")
    @PreAuthorize("hasRole('Administrador')")
    public ResponseEntity<PaymentResponse> reactivatePayment(@PathVariable Integer id) {
        return ResponseEntity.ok(paymentService.reactivateEntity(id));
    }
}
