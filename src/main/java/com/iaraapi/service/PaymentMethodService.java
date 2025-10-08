package com.iaraapi.service;

import com.iaraapi.dto.request.PaymentMethodRequest;
import com.iaraapi.dto.response.PaymentMethodResponse;
import com.iaraapi.mapper.PaymentMethodMapper;
import com.iaraapi.model.PaymentMethod;
import com.iaraapi.repository.PaymentMethodRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;


@Slf4j
@Service
public class PaymentMethodService extends BaseService<PaymentMethod, Integer, PaymentMethodRequest, PaymentMethodResponse> {
    private final PaymentMethodMapper mapper;

    public PaymentMethodService(PaymentMethodRepository repository, PaymentMethodMapper mapper) {
        super(repository, "PaymentMethod");
        this.mapper = mapper;
    }

    @Override
    protected PaymentMethod toEntity(PaymentMethodRequest request) {
        return mapper.toEntity(request);
    }

    @Override
    protected PaymentMethodResponse toResponse(PaymentMethod paymentMethod) {
        return mapper.toResponse(paymentMethod);
    }

    @Override
    protected void updateEntity(PaymentMethod paymentMethod, PaymentMethodRequest request) {
        paymentMethod.setName(request.getName());
    }

    @Override
    @Transactional
    public PaymentMethodResponse deactivateEntity(Integer id) {
        log.info("[PaymentMethodService] [deactivateEntity] DEACTIVATE PAYMENT METHOD WITH ID {}", id);
        PaymentMethod paymentMethod = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Payment method with ID " + id + " not found."));

        paymentMethod.setDeactivatedAt(LocalDateTime.now());
        repository.save(paymentMethod);
        return toResponse(paymentMethod);
    }

    @Override
    @Transactional
    public PaymentMethodResponse reactivateEntity(Integer id) {
        log.info("[PaymentMethodService] [reactivateEntity] REACTIVATE PAYMENT METHOD WITH ID {}", id);
        PaymentMethod paymentMethod = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Payment method with ID " + id + " not found."));

        paymentMethod.setDeactivatedAt(null);
        repository.save(paymentMethod);
        return toResponse(paymentMethod);
    }
}
