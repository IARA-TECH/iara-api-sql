package com.iaraapi.service;

import com.iaraapi.dto.request.AccessTypeRequest;
import com.iaraapi.dto.response.AccessTypeResponse;
import com.iaraapi.mapper.AccessTypeMapper;
import com.iaraapi.model.AccessType;
import com.iaraapi.repository.AccessTypeRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Slf4j
@Service
public class AccessTypeService extends BaseService<AccessType, Long, AccessTypeRequest, AccessTypeResponse> {
    private final AccessTypeMapper mapper;

    public AccessTypeService(AccessTypeRepository repository, AccessTypeMapper mapper) {
        super(repository, "AccessType");
        this.mapper = mapper;
    }

    @Override
    protected AccessType toEntity(AccessTypeRequest request) {
        return mapper.toEntity(request);
    }

    @Override
    protected AccessTypeResponse toResponse(AccessType accessType) {
        return mapper.toResponse(accessType);
    }

    @Override
    protected void updateEntity(AccessType accessType, AccessTypeRequest request) {
        accessType.setName(request.getName());
        accessType.setDescription(request.getDescription());
    }

    @Override
    public AccessTypeResponse deactivateEntity(Long id) {
        log.info("[AccessTypeService] [deactivateEntity] DEACTIVATE ACCESS TYPE WITH ID {}", id);
        AccessType accessType = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Access Type with ID " + id + " not found."));

        accessType.setDeactivatedAt(LocalDateTime.now());
        repository.save(accessType);
        return toResponse(accessType);
    }

    @Override
    public AccessTypeResponse reactivateEntity(Long id) {
        log.info("[PaymentMethodService] [reactivateEntity] REACTIVATE PAYMENT METHOD WITH ID {}", id);
        AccessType accessType = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("PaymentMethod with ID " + id + " not found."));

        accessType.setDeactivatedAt(null);
        repository.save(accessType);
        return toResponse(accessType);
    }
}
