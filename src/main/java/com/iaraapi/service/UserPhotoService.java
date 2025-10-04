package com.iaraapi.service;

import com.iaraapi.dto.request.UserPhotoRequest;
import com.iaraapi.dto.response.UserPhotoResponse;
import com.iaraapi.mapper.UserPhotoMapper;
import com.iaraapi.model.User;
import com.iaraapi.model.UserPhoto;
import com.iaraapi.repository.UserPhotoRepository;
import com.iaraapi.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;

import java.time.LocalDateTime;

public class UserPhotoService extends BaseService<UserPhoto, Long, UserPhotoRequest, UserPhotoResponse> {
    private final UserPhotoMapper mapper;
    private final UserRepository userRepository;

    public UserPhotoService(UserPhotoRepository repository, UserPhotoMapper mapper, UserRepository userRepository) {
        super(repository, "UserPhoto");
        this.mapper = mapper;
        this.userRepository = userRepository;
    }


    @Override
    protected UserPhoto toEntity(UserPhotoRequest request) {
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new EntityNotFoundException("User with ID " + request.getUserId() + " not found"));
        return mapper.toEntity(request, user);
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
    public UserPhotoResponse deactivateEntity(Long aLong) {
        return null;
    }

    @Override
    public UserPhotoResponse reactivateEntity(Long aLong) {
        return null;
    }
}
