package com.iaraapi.service;

import com.iaraapi.dto.response.SubscriptionResponse;
import com.iaraapi.mapper.SubscriptionMapper;
import com.iaraapi.model.Subscription;
import com.iaraapi.dto.request.SubscriptionRequest;
import com.iaraapi.repository.SubscriptionRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class SubscriptionService extends BaseService<Subscription, Long, SubscriptionRequest, SubscriptionResponse> {
    private final SubscriptionMapper mapper;

    public SubscriptionService(JpaRepository<Subscription, Long> repository, SubscriptionMapper mapper) {
        super(repository, "Subscription");
        this.mapper = mapper;
    }

//    @Override
//    public List<SubscriptionResponse> getAll() {
//        return super.getAll();
//    }
//
//    public SubscriptionResponse getSubscriptionById(Long id) {
//        log.info("[SubscriptionService] [getSubscriptionById] GET SUBSCRIPTION BY ID {}", id);
//        return mapper.toResponse(repository.findById(id)
//                .orElseThrow(() -> new EntityNotFoundException("Subscription with ID " + id + " not found.")));
//    }
//
//    public SubscriptionResponse createSubscription(SubscriptionRequest request) {
//        log.info("[SubscriptionService] [createSubscription] Subscription request {}", request);
//        Subscription subscription = mapper.toEntity(request);
//
//        log.info("[SubscriptionService] [createSubscription] Subscription {}", subscription);
//        return mapper.toResponse(repository.save(subscription));
//    }
//
//    public SubscriptionResponse deleteSubscriptionById(Long id) {
//        log.info("[SubscriptionService] [deleteSubscriptionById] Delete Subscription with ID {}", id);
//        Subscription subscription = repository.findById(id)
//                .orElseThrow(() -> new EntityNotFoundException("Subscription with ID "
//                        + id + " not found"));
//
//        log.info("[SubscriptionService] [deleteSubscriptionById] Delete Subscription {}", subscription);
//        repository.delete(subscription);
//
//        return mapper.toResponse(subscription);
//    }
//
//    public SubscriptionResponse updateSubscriptionById(Long id, SubscriptionRequest request) {
//        Subscription subscription = repository.findById(id)
//                .orElseThrow(() -> new EntityNotFoundException("Subscription with ID " + id + " not found."));
//        return mapper.toResponse(updateSubscription(subscription, request));
//
//    }
//
//    private Subscription updateSubscription(Subscription subscription, SubscriptionRequest request) {
//        subscription.setName(request.getName());
//        subscription.setDescription(request.getDescription());
//        subscription.setPrice(request.getPrice());
//        subscription.setMonthlyDuration(request.getMonthlyDuration());
//        return repository.save(subscription);
//    }

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
        repository.save(subscription);
    }

    @Override
    public SubscriptionResponse deactivateEntity(Long id) {
        log.info("[SubscriptionService] [getSubscriptionById] DEACTIVATE SUBSCRIPTION BY ID {}", id);
        Subscription subscription = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Subscription with ID " + id + " not found."));

        subscription.setDeactivatedAt(LocalDateTime.now());
        return toResponse(subscription);
    }

    @Override
    public SubscriptionResponse reactivateEntity(Long id) {
        log.info("[SubscriptionService] [getSubscriptionById] REACTIVATE SUBSCRIPTION BY ID {}", id);
        Subscription subscription = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Subscription with ID " + id + " not found."));
        subscription.setDeactivatedAt(null);
        return toResponse(subscription);
    }
}
