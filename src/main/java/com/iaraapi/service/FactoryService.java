package com.iaraapi.service;

import com.iaraapi.dto.request.FactoryRequest;
import com.iaraapi.dto.response.FactoryResponse;
import com.iaraapi.mapper.FactoryMapper;
import com.iaraapi.model.Factory;
import com.iaraapi.repository.FactoryRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@Slf4j
public class FactoryService extends BaseService<Factory, Integer, FactoryRequest, FactoryResponse> {

    private final FactoryMapper mapper;

    public FactoryService(FactoryRepository repository, FactoryMapper mapper) {
        super(repository, "Factory");
        this.mapper = mapper;
    }

    @Override
    protected Factory toEntity(FactoryRequest request) {
        return mapper.toEntity(request);
    }

    @Override
    protected FactoryResponse toResponse(Factory factory) {
        return mapper.toResponse(factory);
    }

    @Override
    protected void updateEntity(Factory factory, FactoryRequest request) {
        factory.setName(request.getName());
        factory.setDescription(request.getDescription());
        factory.setCnpj(request.getCnpj());
    }

    @Override
    public FactoryResponse deactivateEntity(Integer id) {
        log.info("[FactoryService] [deactivateEntity] DEACTIVATE FACTORY WITH ID {}", id);
        Factory factory = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Factory with ID " + id + " not found."));

        factory.setDeactivatedAt(LocalDateTime.now());
        repository.save(factory);
        return toResponse(factory);
    }

    @Override
    public FactoryResponse reactivateEntity(Integer id) {
        log.info("[FactoryService] [reactivateEntity] REACTIVATE FACTORY WITH ID {}", id);
        Factory factory = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Factory with ID " + id + " not found."));

        factory.setDeactivatedAt(null);
        repository.save(factory);
        return toResponse(factory);
    }
}
