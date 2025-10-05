package com.iaraapi.controller;

import com.iaraapi.dto.request.PaymentRequest;
import com.iaraapi.dto.response.PaymentResponse;
import com.iaraapi.service.PaymentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/payments")
@RequiredArgsConstructor
@CrossOrigin("*")
public class PaymentController {

    private final PaymentService paymentService;

    @PostMapping
    public ResponseEntity<PaymentResponse> createPayment(@RequestBody @Valid PaymentRequest paymentRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(paymentService.create(paymentRequest));
    }

    @GetMapping
    public ResponseEntity<List<PaymentResponse>> getAllPayments() {
        return ResponseEntity.ok(paymentService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PaymentResponse> getPaymentById(@PathVariable Integer id) {
        return ResponseEntity.ok(paymentService.getById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PaymentResponse> updatePayment(@PathVariable Integer id, @RequestBody @Valid PaymentRequest paymentRequest) {
        return ResponseEntity.ok(paymentService.update(id, paymentRequest));
    }

    @PatchMapping("/deactivate/{id}")
    public ResponseEntity<PaymentResponse> deactivatePayment(@PathVariable Integer id) {
        return ResponseEntity.ok(paymentService.deactivateEntity(id));
    }

    @PatchMapping("/reactivate/{id}")
    public ResponseEntity<PaymentResponse> reactivatePayment(@PathVariable Integer id) {
        return ResponseEntity.ok(paymentService.reactivateEntity(id));
    }
}
