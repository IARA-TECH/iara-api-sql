package com.iaraapi.service;

import com.iaraapi.dto.request.UserPhotoRequest;
import com.iaraapi.dto.response.UserPhotoResponse;
import com.iaraapi.mapper.UserPhotoMapper;
import com.iaraapi.model.User;
import com.iaraapi.model.UserPhoto;
import com.iaraapi.repository.UserPhotoRepository;
import com.iaraapi.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;


@Service
@Slf4j
public class UserPhotoService extends BaseService<UserPhoto, Integer, UserPhotoRequest, UserPhotoResponse> {
    private final UserPhotoMapper mapper;
    private final UserRepository userRepository;
    private final UserPhotoRepository userPhotoRepository;

    public UserPhotoService(UserPhotoRepository repository, UserPhotoMapper mapper, UserRepository userRepository) {
        super(repository, "UserPhoto");
        this.mapper = mapper;
        this.userRepository = userRepository;
        this.userPhotoRepository = userPhotoRepository;
    }


    @Override
    protected UserPhoto toEntity(UserPhotoRequest request) {
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new EntityNotFoundException("User with ID " + request.getUserId() + " not found"));
        return mapper.toEntity(request, user);
    }

    @Override
    public UserPhotoResponse create(UserPhotoRequest request) {
        log.info("[UserAccountPhotoService] Creating photo for user {}", request.getUserId());
        try {
            userPhotoRepository.createUserAccountPhoto(request.getUserId(), request.getUrlBlob());
            log.info("[UserAccountPhotoService] Photo created successfully for user {}", request.getUserId());
            return new UserPhotoResponse(
                    request.getUrlBlob(),
                    request.getUserId()
            );
        } catch (Exception e) {
            log.error("[UserAccountPhotoService] Error creating user photo: {}", e.getMessage(), e);
            throw new RuntimeException("Error creating user photo.");
        }
    }

    @Override
    protected UserPhotoResponse toResponse(UserPhoto userPhoto) {
        return mapper.toResponse(userPhoto);
    }

    @Override
    protected void updateEntity(UserPhoto userPhoto, UserPhotoRequest request) {
        userPhoto.setUrlBlob(request.getUrlBlob());
        userPhoto.setChangedAt(LocalDateTime.now());
    }

    @Override
    public UserPhotoResponse deactivateEntity(Integer id) {
        return null;
    }

    @Override
    public UserPhotoResponse reactivateEntity(Integer id) {
        return null;
    }
}
