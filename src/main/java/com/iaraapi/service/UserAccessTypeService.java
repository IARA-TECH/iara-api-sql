package com.iaraapi.service;

import com.iaraapi.dto.request.UserAccessTypeRequest;
import com.iaraapi.dto.response.UserAccessTypeResponse;
import com.iaraapi.mapper.UserAccessTypeMapper;
import com.iaraapi.model.*;
import com.iaraapi.repository.*;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserAccessTypeService {

    private final UserAccessTypeRepository repository;
    private final UserRepository userRepository;
    private final AccessTypeRepository accessTypeRepository;
    private final UserAccessTypeMapper mapper;

    @Transactional
    public UserAccessTypeResponse create(UserAccessTypeRequest request) {
        log.info("[UserAccessTypeService] [create] Creating relation user={} accessType={}", request.getUserId(), request.getAccessTypeId());

        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new EntityNotFoundException("User not found with ID: " + request.getUserId()));

        AccessType accessType = accessTypeRepository.findById(request.getAccessTypeId())
                .orElseThrow(() -> new EntityNotFoundException("Access type with ID " + request.getAccessTypeId() + " not found with ID"));

        UserAccessType entity = new UserAccessType();
        entity.setId(getUserAccessTypeId(user.getId(), accessType.getId()));
        entity.setUser(user);
        entity.setAccessType(accessType);

        return mapper.toResponse(repository.save(entity));
    }

    public List<UserAccessTypeResponse> getAllByUserId(UUID userId) {
        log.info("[UserAccessTypeService] [getAllByUserId] Getting all access types for user={}", userId);

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User with ID " + userId + " not found."));

        return user.getUserAccessTypes().stream()
                .filter(uat -> uat.getDeactivatedAt() == null)
                .map(mapper::toResponse)
                .toList();
    }

    @Transactional
    public UserAccessTypeResponse deactivate(UUID userId, Integer accessTypeId) {
        log.info("[UserAccessTypeService] [deactivate] Deactivating relation user={} accessType={}", userId, accessTypeId);

        UserAccessTypeId id = getUserAccessTypeId(userId, accessTypeId);
        UserAccessType entity = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Relation not found for given IDs."));

        entity.setDeactivatedAt(LocalDateTime.now());

        return mapper.toResponse(repository.save(entity));
    }

    @Transactional
    public UserAccessTypeResponse reactivate(UUID userId, Integer accessTypeId) {
        log.info("[UserAccessTypeService] [reactivate] Reactivating relation user={} accessType={}", userId, accessTypeId);

        UserAccessTypeId id = getUserAccessTypeId(userId, accessTypeId);
        UserAccessType entity = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Relation not found for given IDs."));

        entity.setDeactivatedAt(null);

        return mapper.toResponse(repository.save(entity));
    }

    private UserAccessTypeId getUserAccessTypeId(UUID userId, Integer accessTypeId) {
        return new UserAccessTypeId(userId, accessTypeId);
    }
}
