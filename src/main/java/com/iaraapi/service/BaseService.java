package com.iaraapi.service;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
public abstract class BaseService<E, ID, Req, Res> {
    protected final JpaRepository<E, ID> repository;
    private final String entityName;

    protected abstract E toEntity(Req request);
    protected abstract Res toResponse(E entity);
    protected abstract void updateEntity(E entity, Req request);
    public abstract Res deactivateEntity(ID id);
    public abstract Res reactivateEntity(ID id);

    public List<Res> getAll() {
        log.info("[{}Service] [getAll] GET ALL", entityName);
        return repository.findAll().stream()
                .map(this::toResponse)
                .toList();
    }

    public Res getById(ID id) {
        log.info("[{}Service] [getById] GET BY ID {}", entityName, id);
        E entity = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(entityName + " with ID " + id + " not found."));
        return toResponse(entity);
    }

    @Transactional
    public Res create(Req request) {
        log.info("[{}Service] [create] CREATE", entityName);
        E entity = toEntity(request);
        return toResponse(repository.save(entity));
    }

    @Transactional
    public Res update(ID id, Req request) {
        log.info("[{}Service] [updateById] UPDATE WITH ID {}", entityName, id);
        E entity = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(entityName + " with ID " + id + " not found."));
        log.info("[{}Service] [updateById] entity = {}", entityName, entity);
        updateEntity(entity, request);
        return toResponse(repository.save(entity));
    }
}
