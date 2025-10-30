package com.iaraapi.service;

import com.iaraapi.model.dto.request.UserAccessTypeRequest;
import com.iaraapi.model.dto.response.UserAccessTypeResponse;
import com.iaraapi.model.mapper.UserAccessTypeMapper;
import com.iaraapi.model.database.AccessType;
import com.iaraapi.model.database.User;
import com.iaraapi.model.database.UserAccessType;
import com.iaraapi.model.database.UserAccessTypeId;
import com.iaraapi.repository.*;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

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
                .orElseThrow(() -> new EntityNotFoundException("Access type with ID " + request.getAccessTypeId() + " not found."));

        UserAccessType entity = new UserAccessType();
        entity.setId(getUserAccessTypeId(user.getId(), accessType.getId()));
        entity.setUser(user);
        entity.setAccessType(accessType);

        return mapper.toResponse(repository.save(entity));
    }

    public List<UserAccessTypeResponse> getAllByUserId(UUID userId) {
        log.info("[UserAccessTypeService] [getAllByUserId] Getting all access types for user={}", userId);

        List<UserAccessType> accessTypes = repository.findByUser_Id(userId);

        return accessTypes.stream()
                .map(mapper::toResponse)
                .toList();
    }

    @Transactional
    public String deleteByUserId(UUID userId, Integer accessTypeId) {
        log.info("[UserAccessTypeService] [deleteByUserId] Deleting access type with id={} for user={}", accessTypeId, userId);
        repository.deleteByUser_IdAndAccessType_Id(userId, accessTypeId);
        return "Access Type " + accessTypeId + " deleted for user " + userId;
    }

    private UserAccessTypeId getUserAccessTypeId(UUID userId, Integer accessTypeId) {
        return new UserAccessTypeId(userId, accessTypeId);
    }
}
