package com.iaraapi.service;

import com.iaraapi.model.dto.request.UserPhotoRequest;
import com.iaraapi.model.dto.response.UserPhotoResponse;
import com.iaraapi.model.mapper.UserPhotoMapper;
import com.iaraapi.model.database.User;
import com.iaraapi.model.database.UserPhoto;
import com.iaraapi.repository.UserPhotoRepository;
import com.iaraapi.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;


@Service
@Slf4j
public class UserPhotoService {
    private final UserPhotoMapper mapper;
    private final UserRepository userRepository;
    private final UserPhotoRepository repository;

    public UserPhotoService(UserPhotoRepository repository, UserPhotoMapper mapper, UserRepository userRepository) {
        this.mapper = mapper;
        this.userRepository = userRepository;
        this.repository = repository;
    }


    protected UserPhoto toEntity(UserPhotoRequest request) {
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new EntityNotFoundException("User with ID " + request.getUserId() + " not found"));
        return mapper.toEntity(request, user);
    }


    protected UserPhotoResponse toResponse(UserPhoto userPhoto) {
        return mapper.toResponse(userPhoto);
    }

    @Transactional
    public UserPhotoResponse create(UserPhotoRequest request) {
        log.info("[UserPhotoService] Creating photo for user {}", request.getUserId());
        try {
            repository.createUserAccountPhoto(request.getUserId(), request.getUrlBlob());
            log.info("[UserPhotoService] Photo created successfully for user {}", request.getUserId());
            return new UserPhotoResponse(
                    request.getUrlBlob(),
                    request.getUserId()
            );
        } catch (Exception e) {
            log.error("[UserPhotoService] Error creating user photo: {}", e.getMessage(), e);
            throw new RuntimeException("Error creating user photo.");
        }
    }

    public UserPhotoResponse updateByUserId(UUID userId, UserPhotoRequest request) {
            log.info("[UserPhotoService] [updateByUserId] UPDATE WITH USER ID {}", userId);
            UserPhoto photo = repository.findByUser_id(userId)
                    .orElseThrow(() -> new EntityNotFoundException("Photo with User ID " + userId + " not found."));
            log.info("[UserPhotoService] [updateByUserId] photo = {}", photo);
            updateEntity(photo, request);
            return toResponse(repository.save(photo));
    }

    public UserPhotoResponse getPhotoByUserId(UUID userId) {
        return toResponse(repository.findByUser_id(userId)
                .orElseThrow(() -> new EntityNotFoundException("User with ID " + userId + " may not have a photo. ")));
    }

    protected void updateEntity(UserPhoto userPhoto, UserPhotoRequest request) {
        userPhoto.setUrlBlob(request.getUrlBlob());
        userPhoto.setChangedAt(LocalDateTime.now());
    }
}
