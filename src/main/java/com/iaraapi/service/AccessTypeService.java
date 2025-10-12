package com.iaraapi.service;

import com.iaraapi.model.dto.request.AccessTypeRequest;
import com.iaraapi.model.dto.response.AccessTypeResponse;
import com.iaraapi.model.mapper.AccessTypeMapper;
import com.iaraapi.model.database.AccessType;
import com.iaraapi.repository.AccessTypeRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Slf4j
@Service
public class AccessTypeService extends BaseService<AccessType, Integer, AccessTypeRequest, AccessTypeResponse> {
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
    @Transactional
    public AccessTypeResponse deactivateEntity(Integer id) {
        log.info("[AccessTypeService] [deactivateEntity] DEACTIVATE ACCESS TYPE WITH ID {}", id);
        AccessType accessType = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Access Type with ID " + id + " not found."));

        accessType.setDeactivatedAt(LocalDateTime.now());
        repository.save(accessType);
        return toResponse(accessType);
    }

    @Override
    @Transactional
    public AccessTypeResponse reactivateEntity(Integer id) {
        log.info("[AccessTypeService] [reactivateEntity] REACTIVATE ACCESS TYPE WITH ID {}", id);
        AccessType accessType = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Access type with ID " + id + " not found."));

        accessType.setDeactivatedAt(null);
        repository.save(accessType);
        return toResponse(accessType);
    }
}
