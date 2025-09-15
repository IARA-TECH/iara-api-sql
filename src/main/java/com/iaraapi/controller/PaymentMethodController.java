package com.iaraapi.controller;

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
@RequestMapping("/api/v1")
@RequiredArgsConstructor
@CrossOrigin("*")
public class PaymentMethodController {
    private final PaymentMethodService paymentMethodService;

    @PostMapping("/payment-methods")
    public ResponseEntity<PaymentMethodResponse> createPaymentMethod(@RequestBody PaymentMethodRequest paymentMethodRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(paymentMethodService.createPaymentMethod(paymentMethodRequest));
    }

    @GetMapping("/payment-methods")
    public ResponseEntity<List<PaymentMethodResponse>> getAllPaymentMethods() {
        return ResponseEntity.ok(paymentMethodService.getAllPaymentMethods());
    }

    @GetMapping("/payment-methods/{id}")
    public ResponseEntity<PaymentMethodResponse> getPaymentMethod(@PathVariable Long id) {
        return ResponseEntity.ok(paymentMethodService.getPaymentMethodById(id));
    }

    @PutMapping("/payment-methods/{id}")
    public ResponseEntity<PaymentMethodResponse> updatePaymentMethod(@PathVariable Long id,
                                                                     @RequestBody @Valid PaymentMethodRequest paymentMethodRequest) {
        return ResponseEntity.ok(paymentMethodService.updatePaymentMethodById(id, paymentMethodRequest));
    }

    @DeleteMapping("/payment-methods/{id}")
    public ResponseEntity<PaymentMethodResponse> deletePaymentMethod(@PathVariable Long id) {
        return ResponseEntity.ok(paymentMethodService.deletePaymentMethodById(id));
    }
}
