package com.iaraapi.service;

import com.iaraapi.dto.response.SubscriptionResponse;
import com.iaraapi.mapper.SubscriptionMapper;
import com.iaraapi.model.Subscription;
import com.iaraapi.dto.request.SubscriptionRequest;
import com.iaraapi.repository.SubscriptionRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Service
public class SubscriptionService {
    private final SubscriptionRepository subscriptionRepository;
    private final SubscriptionMapper subscriptionMapper;

    public List<SubscriptionResponse> getAllSubscriptions() {
        log.info("[SubscriptionService] [getAllSubscriptions] START GET SUBSCRIPTIONS");
        return subscriptionRepository.findAll()
                .stream()
                .map(subscriptionMapper::toResponse)
                .toList();

    }

    public SubscriptionResponse getSubscriptionById(Long id) {
        log.info("[SubscriptionService] [getSubscriptionById] GET SUBSCRIPTION BY ID {}", id);
        return subscriptionMapper.toResponse(subscriptionRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Subscription with ID " + id + " not found.")));
    }

    public SubscriptionResponse createSubscription(SubscriptionRequest request) {
        log.info("[SubscriptionService] [createSubscription] Subscription request {}", request);
        Subscription subscription = subscriptionMapper.toEntity(request);

        log.info("[SubscriptionService] [createSubscription] Subscription {}", subscription);
        return subscriptionMapper.toResponse(subscriptionRepository.save(subscription));
    }

    public SubscriptionResponse deleteSubscriptionById(Long id) {
        log.info("[SubscriptionService] [deleteSubscriptionById] Delete Subscription with ID {}", id);
        Subscription subscription = subscriptionRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Subscription with ID "
                        + id + " not found"));

        log.info("[SubscriptionService] [deleteSubscriptionById] Delete Subscription {}", subscription);
        subscriptionRepository.delete(subscription);

        return subscriptionMapper.toResponse(subscription);
    }

    public SubscriptionResponse updateSubscriptionById(Long id, SubscriptionRequest request) {
        Optional<Subscription> optionalSubscription = subscriptionRepository.findById(id);
        if (optionalSubscription.isPresent()) {
            Subscription subscription = optionalSubscription.get();
            return subscriptionMapper.toResponse(updateSubscription(subscription, request));
        }
        throw new EntityNotFoundException("Subscription with ID " + id + " not found.");
    }

    private Subscription updateSubscription(Subscription subscription, SubscriptionRequest request) {
        subscription.setName(request.getName());
        subscription.setPrice(request.getPrice());
        subscription.setMonthlyDuration(request.getMonthlyDuration());
        subscriptionRepository.save(subscription);
        return subscription;
    }

}
