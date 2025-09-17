package com.iaraapi.service;

import com.iaraapi.dto.request.PaymentMethodRequest;
import com.iaraapi.dto.response.PaymentMethodResponse;
import com.iaraapi.mapper.PaymentMethodMapper;
import com.iaraapi.model.PaymentMethod;
import com.iaraapi.repository.PaymentMethodRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;


@Slf4j
@RequiredArgsConstructor
@Service
public class PaymentMethodService {
    private final PaymentMethodRepository repository;
    private final PaymentMethodMapper mapper;

    public List<PaymentMethodResponse> getAllPaymentMethods() {
        log.info("[PaymentMethodService] [getAllPaymentMethods] GET ALL PAYMENT METHODS");
        return repository.findAll()
                .stream()
                .map(mapper::toResponse)
                .toList();

    }

    public PaymentMethodResponse getPaymentMethodById(Long id) {
        log.info("[PaymentMethodService] [getPaymentMethodById] GET PAYMENT METHOD BY ID]");
        return mapper.toResponse(repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Payment method with ID " + id + " not found.")));
    }

    public PaymentMethodResponse createPaymentMethod(PaymentMethodRequest request) {
        log.info("[PaymentMethodService] [createPaymentMethod] Payment method request {}", request);
        PaymentMethod paymentMethod = mapper.toEntity(request);

        log.info("[PaymentMethodService] [createPaymentMethod] Payment method {}", paymentMethod);
        return mapper.toResponse(repository.save(paymentMethod));
    }

    public PaymentMethodResponse deletePaymentMethodById(Long id) {
        log.info("[PaymentMethodService] [deletePaymentMethodById] Delete Payment method with ID {}", id);
        PaymentMethod paymentMethod = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Payment method with ID " + id + " not found."));

        log.info("[PaymentMethodService] [deletePaymentMethodById] Delete Payment method {}", paymentMethod);
        repository.delete(paymentMethod);

        return mapper.toResponse(paymentMethod);
    }

    public PaymentMethodResponse updatePaymentMethodById(Long id, PaymentMethodRequest request) {
        log.info("[PaymentMethodService] [updatePaymentMethodById] Update Payment method with ID {}", id);
        PaymentMethod paymentMethod = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Payment method with ID " + id + " not found."));
        log.info("[PaymentMethodService] [updatePaymentMethodById] Payment method {}", paymentMethod);
        return mapper.toResponse(updatePaymentMethod(paymentMethod, request));
    }

    private PaymentMethod updatePaymentMethod(PaymentMethod paymentMethod, PaymentMethodRequest request) {
        paymentMethod.setName(request.getName());
        repository.save(paymentMethod);
        return paymentMethod;
    }
}
