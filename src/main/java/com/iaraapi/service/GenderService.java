package com.iaraapi.service;

import com.iaraapi.dto.request.GenderRequest;
import com.iaraapi.dto.response.GenderResponse;
import com.iaraapi.mapper.GenderMapper;
import com.iaraapi.model.Gender;
import com.iaraapi.repository.GenderRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;


@Slf4j
@Service
public class GenderService extends BaseService<Gender, Integer, GenderRequest, GenderResponse> {
    private final GenderMapper mapper;

    public GenderService(GenderRepository repository, GenderMapper mapper) {
        super(repository, "Gender");
        this.mapper = mapper;
    }


    @Override
    protected Gender toEntity(GenderRequest request) {
        return mapper.toEntity(request);
    }

    @Override
    protected GenderResponse toResponse(Gender gender) {
        return mapper.toResponse(gender);
    }

    @Override
    protected void updateEntity(Gender gender, GenderRequest request) {
        gender.setName(request.getName());
    }

    @Override
    public GenderResponse deactivateEntity(Integer id) {
        log.info("[GenderService] [deactivateEntity] DEACTIVATE GENDER WITH ID {}", id);
        Gender gender = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Gender with ID " + id + " not found."));

        gender.setDeactivatedAt(LocalDateTime.now());
        repository.save(gender);
        return toResponse(gender);
    }

    @Override
    public GenderResponse reactivateEntity(Integer id) {
        log.info("[GenderService] [reactivateEntity] REACTIVATE GENDER WITH ID {}", id);
        Gender gender = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Gender with ID " + id + " not found."));

        gender.setDeactivatedAt(null);
        repository.save(gender);
        return toResponse(gender);
    }
}
