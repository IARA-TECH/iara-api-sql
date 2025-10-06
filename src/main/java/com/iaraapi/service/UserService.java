package com.iaraapi.service;

import com.iaraapi.dto.request.UserRequest;
import com.iaraapi.dto.response.UserResponse;
import com.iaraapi.mapper.UserMapper;
import com.iaraapi.model.Factory;
import com.iaraapi.model.Gender;
import com.iaraapi.model.User;
import com.iaraapi.repository.FactoryRepository;
import com.iaraapi.repository.GenderRepository;
import com.iaraapi.repository.UserRepository;
import com.iaraapi.util.PasswordUtil;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@Slf4j
public class UserService extends BaseService<User, UUID, UserRequest, UserResponse>{

    private final UserRepository userRepository;
    private final GenderRepository genderRepository;
    private final FactoryRepository factoryRepository;
    private final UserMapper mapper;

    public UserService(UserRepository repository, UserMapper mapper, GenderRepository genderRepository, FactoryRepository factoryRepository) {
        super(repository, "User");
        this.userRepository = repository;
        this.factoryRepository = factoryRepository;
        this.genderRepository = genderRepository;
        this.mapper = mapper;
    }

    @Override
    public UserResponse create(UserRequest request) {
        log.info("[UserService] Creating user {}", request.getName());

        String randomPassword = PasswordUtil.generateRandomPassword();

        try {
            userRepository.createUserAccount(
                    request.getName(),
                    request.getEmail(),
                    randomPassword,
                    request.getDateOfBirth(),
                    request.getGenderId(),
                    request.getFactoryId(),
                    request.getUserManagerId()
            );

            User user = userRepository.findByEmail(request.getEmail())
                    .orElseThrow(() -> new EntityNotFoundException("User not found after creation"));

            return toResponse(user);

        } catch (Exception e) {
            log.error("[UserService] Error creating user: {}", e.getMessage(), e);
            throw new RuntimeException("Error creating user");
        }
    }


    @Override
    protected User toEntity(UserRequest request) {
        return mapper.toEntity(request, getGender(request.getGenderId()), getFactory(request.getFactoryId()));
    }

    @Override
    protected UserResponse toResponse(User user) {
        return mapper.toResponse(user, getUserManagerName(user));
    }

    @Override
    protected void updateEntity(User user, UserRequest request) {
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setDateOfBirth(request.getDateOfBirth());

        if (request.getPassword() != null && !request.getPassword().isBlank()) {
            user.setPassword(request.getPassword());
        }

        user.setUserManagerId(request.getUserManagerId());

        user.setGender(getGender(request.getGenderId()));

        user.setFactory(getFactory(request.getFactoryId()));
    }


    @Override
    public UserResponse deactivateEntity(UUID id) {
        log.info("[UserService] [deactivateEntity] DEACTIVATE USER WITH ID {}", id);
        User user = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User with ID " + id + " not found."));

        user.setDeactivatedAt(LocalDateTime.now());
        repository.save(user);
        return toResponse(user);
    }

    @Override
    public UserResponse reactivateEntity(UUID id) {
        log.info("[UserService] [reactivateEntity] DEACTIVATE USER WITH ID {}", id);
        User user = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User with ID " + id + " not found."));

        user.setDeactivatedAt(null);
        repository.save(user);
        return toResponse(user);
    }

    public UserResponse getUserByEmail(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new EntityNotFoundException("User with email " + email + " not found."));
        return mapper.toResponse(user, getUserManagerName(user));
    }

    public UserResponse getUserByName(String name) {
        User user = userRepository.findByName(name)
                .orElseThrow(() -> new EntityNotFoundException("User with name " + name + " not found."));
        return mapper.toResponse(user, getUserManagerName(user));
    }

    private String getUserManagerName(User user) {
        User userManager = userRepository.findById(user.getUserManagerId())
                .orElseThrow(() -> new EntityNotFoundException("User with ID " + user.getUserManagerId() + " not found."));

        return userManager.getName();
    }

    private Gender getGender(Integer id) {
        return genderRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Gender with ID "+ id +" not found"));
    }

    private Factory getFactory(Integer id) {
        return factoryRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Factory with ID " + id + "not found"));
    }
}
