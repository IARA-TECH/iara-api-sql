package com.iaraapi.service;

import com.iaraapi.dto.response.SubscriptionResponse;
import com.iaraapi.mapper.SubscriptionMapper;
import com.iaraapi.model.Subscription;
import com.iaraapi.dto.request.SubscriptionRequest;
import com.iaraapi.repository.SubscriptionRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class SubscriptionService extends BaseService<Subscription, Integer, SubscriptionRequest, SubscriptionResponse> {
    private final SubscriptionMapper mapper;

    public SubscriptionService(JpaRepository<Subscription, Integer> repository, SubscriptionMapper mapper) {
        super(repository, "Subscription");
        this.mapper = mapper;
    }

    @Override
    protected Subscription toEntity(SubscriptionRequest request) {
        return mapper.toEntity(request);
    }

    @Override
    protected SubscriptionResponse toResponse(Subscription subscription) {
        return mapper.toResponse(subscription);
    }

    @Override
    protected void updateEntity(Subscription subscription, SubscriptionRequest request) {
        subscription.setName(request.getName());
        subscription.setDescription(request.getDescription());
        subscription.setPrice(request.getPrice());
        subscription.setMonthlyDuration(request.getMonthlyDuration());
    }

    @Override
    @Transactional
    public SubscriptionResponse deactivateEntity(Integer id) {
        log.info("[SubscriptionService] [deactivateEntity] DEACTIVATE SUBSCRIPTION WITH ID {}", id);
        Subscription subscription = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Subscription with ID " + id + " not found."));

        subscription.setDeactivatedAt(LocalDateTime.now());
        repository.save(subscription);
        return toResponse(subscription);
    }

    @Override
    @Transactional
    public SubscriptionResponse reactivateEntity(Integer id) {
        log.info("[SubscriptionService] [reactivateEntity] REACTIVATE SUBSCRIPTION WITH ID {}", id);
        Subscription subscription = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Subscription with ID " + id + " not found."));
        subscription.setDeactivatedAt(null);
        repository.save(subscription);
        return toResponse(subscription);
    }
}
