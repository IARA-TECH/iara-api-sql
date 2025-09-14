package com.iaraapi.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.iaraapi.dto.response.SubscriptionResponse;
import com.iaraapi.model.Subscription;
import com.iaraapi.dto.request.SubscriptionRequest;
import com.iaraapi.repository.SubscriptionRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class SubscriptionService {
    private final SubscriptionRepository subscriptionRepository;
    private final ObjectMapper objectMapper;

    public List<SubscriptionResponse> getAllSubscriptions() {
        return subscriptionRepository.findAll()
                .stream()
                .map(product -> objectMapper.convertValue(product, SubscriptionResponse.class))
                .toList();

    }

    public SubscriptionResponse getSubscriptionById(Long id) {
        return objectMapper.convertValue(subscriptionRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Subscription with ID "
                        + id + " not found.")),
                SubscriptionResponse.class);
    }

    public SubscriptionResponse createSubscription(SubscriptionRequest request) {
        Subscription subscription = objectMapper.convertValue(request, Subscription.class);
        return objectMapper.convertValue(subscriptionRepository.save(subscription), SubscriptionResponse.class);
    }

    public SubscriptionResponse deleteSubscriptionById(Long id) {
        Subscription subscription = subscriptionRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Subscription with ID "
                        + id + " not found"));
        subscriptionRepository.delete(subscription);
        return objectMapper.convertValue(subscription, SubscriptionResponse.class);
    }

    public SubscriptionResponse updateSubscriptionById(Long id, SubscriptionRequest request) {
        Optional<Subscription> optionalSubscription = subscriptionRepository.findById(id);
        if (optionalSubscription.isPresent()) {
           return objectMapper.convertValue(updateSubscription(optionalSubscription, request), SubscriptionResponse.class);
        }
        throw new EntityNotFoundException("Subscription with ID " + id + " not found.");
    }

    private Subscription updateSubscription(Optional<Subscription> optionalSubscription, SubscriptionRequest request) {
        Subscription subscription = optionalSubscription.get();
        subscription.setName(request.getName());
        subscription.setPrice(request.getPrice());
        subscription.setMonthlyDuration(request.getMonthlyDuration());
        subscriptionRepository.save(subscription);
        return subscription;
    }

}
