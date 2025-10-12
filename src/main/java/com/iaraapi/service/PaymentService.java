package com.iaraapi.service;

import com.iaraapi.model.dto.request.PaymentRequest;
import com.iaraapi.model.dto.response.PaymentResponse;
import com.iaraapi.model.mapper.PaymentMapper;
import com.iaraapi.model.database.Payment;
import com.iaraapi.model.database.PaymentMethod;
import com.iaraapi.model.database.Subscription;
import com.iaraapi.model.database.User;
import com.iaraapi.repository.PaymentMethodRepository;
import com.iaraapi.repository.PaymentRepository;
import com.iaraapi.repository.SubscriptionRepository;
import com.iaraapi.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.UUID;

@Slf4j
@Service
public class PaymentService extends BaseService<Payment, Integer, PaymentRequest, PaymentResponse> {

    private final PaymentMapper mapper;
    private final PaymentRepository paymentRepository;
    private final PaymentMethodRepository paymentMethodRepository;
    private final SubscriptionRepository subscriptionRepository;
    private final UserRepository userRepository;

    public PaymentService(PaymentRepository repository, PaymentMapper mapper,
                          PaymentMethodRepository paymentMethodRepository, SubscriptionRepository subscriptionRepository,
                          UserRepository userRepository) {
        super(repository, "Payment");
        this.mapper = mapper;
        this.paymentRepository = repository;
        this.subscriptionRepository = subscriptionRepository;
        this.paymentMethodRepository = paymentMethodRepository;
        this.userRepository = userRepository;
    }

    @Override
    protected Payment toEntity(PaymentRequest request) {
        PaymentMethod paymentMethod = getPaymentMethod(request.getPaymentMethodId());
        Subscription subscription = getSubscription(request.getSubscriptionId());
        User user = getUser(request.getUserAccountUid());

        return mapper.toEntity(request, subscription, paymentMethod, user);
    }

    @Override
    protected PaymentResponse toResponse(Payment entity) {
        return mapper.toResponse(entity);
    }

    @Override
    @Transactional
    public PaymentResponse create(PaymentRequest request) {
        log.info("[PaymentService] [create] CREATE PAYMENT {}", request);

        try {
            paymentRepository.createPayment(request.getUserAccountUid(), request.getSubscriptionId(),
                    request.getPaymentMethodId());

            Payment payment = paymentRepository
                    .findTopByUser_IdOrderByPaidAtDesc(request.getUserAccountUid())
                    .orElseThrow(() -> new EntityNotFoundException("Failed to retrieve the created payment."));

            return toResponse(payment);

        } catch (Exception e) {
            log.error("[PaymentService] [create] ERROR CREATING PAYMENT: {}", e.getMessage());
            throw new RuntimeException("Error creating payment.");
        }
    }

    @Override
    @Transactional
    public PaymentResponse update(Integer id, PaymentRequest request) {
        log.info("[PaymentService] [update] UPDATE WITH ID {}", id);

        Payment payment = paymentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Payment with ID " + id + " not found."));

        if (payment.getIsExpired()) {
            log.info("[PaymentService] [update] PAYMENT WITH {} IS EXPIRED", id);

            throw new IllegalStateException("Cannot update an expired payment.");
        }

        updateEntity(payment, request);
        paymentRepository.save(payment);

        log.info("[PaymentService] [update] PAYMENT WITH ID {} UPDATED SUCCESSFULLY", id);
        return toResponse(payment);
    }

    @Override
    protected void updateEntity(Payment entity, PaymentRequest request) {
        if (request.getPaymentMethodId() != null) {
            PaymentMethod method = getPaymentMethod(request.getPaymentMethodId());
            entity.setPaymentMethod(method);
        }
    }


    @Override
    @Transactional
    public PaymentResponse deactivateEntity(Integer id) {
        log.info("[PaymentService] [deactivateEntity] DEACTIVATE PAYMENT WITH ID {}", id);
        Payment payment = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Payment with ID " + id + " not found."));

        payment.setIsActive(false);
        payment.setIsExpired(true);
        payment.setExpiresOn(LocalDate.now());
        repository.save(payment);

        return toResponse(payment);
    }

    @Override
    @Transactional
    public PaymentResponse reactivateEntity(Integer id) {
        log.info("[PaymentService] [reactivateEntity] REACTIVATE PAYMENT WITH ID {}", id);
        Payment payment = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Payment with ID " + id + " not found."));

        payment.setIsActive(true);
        payment.setIsExpired(false);
        payment.setExpiresOn(payment.getExpiresOn().plusMonths(1)); // renova por +1 mês (ajustável)
        repository.save(payment);

        return toResponse(payment);
    }

    private User getUser(UUID id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User with ID " + id + " not found"));
    }

    private Subscription getSubscription(Integer id) {
        return subscriptionRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Subscription with ID " +
                        id + " not found"));
    }

    private PaymentMethod getPaymentMethod(Integer id) {
        return paymentMethodRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Payment method with ID " +
                        id + " not Found"));
    }
}
