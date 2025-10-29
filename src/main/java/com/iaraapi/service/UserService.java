package com.iaraapi.service;

import com.iaraapi.model.database.*;
import com.iaraapi.model.dto.request.UserRequest;
import com.iaraapi.model.dto.response.UserResponse;
import com.iaraapi.model.mapper.UserMapper;
import com.iaraapi.repository.*;
import com.iaraapi.util.PasswordUtil;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Slf4j
public class UserService extends BaseService<User, UUID, UserRequest, UserResponse>{

    private final UserRepository userRepository;
    private final GenderRepository genderRepository;
    private final FactoryRepository factoryRepository;
    private final UserPhotoRepository userPhotoRepository;
    private final UserMapper mapper;
    private final PasswordEncoder passwordEncoder;
    private final UserAccessTypeRepository userAccessTypeRepository;

    public UserService(UserRepository repository, UserMapper mapper,
                       GenderRepository genderRepository,
                       FactoryRepository factoryRepository,
                       UserPhotoRepository userPhotoRepository,
                       PasswordEncoder passwordEncoder,
                       UserAccessTypeRepository userAccessTypeRepository) {
        super(repository, "User");
        this.userRepository = repository;
        this.factoryRepository = factoryRepository;
        this.genderRepository = genderRepository;
        this.mapper = mapper;
        this.userPhotoRepository = userPhotoRepository;
        this.passwordEncoder = passwordEncoder;
        this.userAccessTypeRepository = userAccessTypeRepository;
    }

    @Override
    @Transactional
    public UserResponse create(UserRequest request) {
        log.info("[UserService] Creating user {}", request.getName());
        String password;

        if (request.getPassword() == null || request.getPassword().isEmpty()) {
            password = PasswordUtil.generateRandomPassword();
        }
        else {
            password = request.getPassword();
        }

        userRepository.createUserAccount(
                request.getName(),
                request.getEmail(),
                passwordEncoder.encode(password),
                request.getDateOfBirth(),
                request.getGenderId(),
                request.getFactoryId(),
                request.getUserManagerId()
        );

        User user = userRepository.findByEmailIgnoreCase(request.getEmail())
                .orElseThrow(() -> new EntityNotFoundException("User not found after creation"));

        return toResponse(user);

    }


    @Override
    protected User toEntity(UserRequest request) {
        return mapper.toEntity(request, getGender(request.getGenderId()), getFactory(request.getFactoryId()));
    }

    @Override
    protected UserResponse toResponse(User user) {
        return mapper.toResponse(user, getUserManagerName(user), getUserPhotoUrl(user.getId()), getUserAccessTypeNames(user.getId()));
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

        user.setChangedAt(LocalDateTime.now());
    }


    @Override
    @Transactional
    public UserResponse deactivateEntity(UUID id) {
        log.info("[UserService] [deactivateEntity] DEACTIVATE USER WITH ID {}", id);
        User user = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User with ID " + id + " not found."));

        user.setDeactivatedAt(LocalDateTime.now());
        repository.save(user);
        return toResponse(user);
    }

    @Override
    @Transactional
    public UserResponse reactivateEntity(UUID id) {
        log.info("[UserService] [reactivateEntity] DEACTIVATE USER WITH ID {}", id);
        User user = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User with ID " + id + " not found."));

        user.setDeactivatedAt(null);
        repository.save(user);
        return toResponse(user);
    }

    public UserResponse getUserByEmail(String email) {
        User user = userRepository.findByEmailIgnoreCase(email)
                .orElseThrow(() -> new EntityNotFoundException("User with email " + email + " not found."));
        return toResponse(user);
    }

    public List<UserResponse> getUserByName(String name) {
        List<User> users = userRepository.findByNameContainsIgnoreCase(name)
                .orElseThrow(() -> new EntityNotFoundException("User with name " + name + " not found."));

        if (users.isEmpty()) {
            throw new EntityNotFoundException("User with name " + name + " not found.");
        }

        return users.stream()
                .map(this::toResponse)
                .toList();
    }

    public List<UserResponse> getUsersByFactory(Integer factoryId) {
        List<User> users = userRepository.findAllByFactory_Id(factoryId);

        if (users.isEmpty()) {
            return List.of();
        }

        return users.stream()
                .map(this::toResponse)
                .toList();
    }


    private String getUserManagerName(User user) {
        if (user.getUserManagerId() == null) return null;
        return userRepository.findById(user.getUserManagerId())
                .map(User::getName)
                .orElseThrow(() -> new EntityNotFoundException("User with ID " + user.getUserManagerId() + " not found."));
    }

    private Gender getGender(Integer id) {
        return genderRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Gender with ID "+ id +" not found"));
    }

    private Factory getFactory(Integer id) {
        return factoryRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Factory with ID " + id + "not found"));
    }

    private String getUserPhotoUrl(UUID userId){
        Optional<UserPhoto> userPhoto = userPhotoRepository.findByUser_id(userId);
        return userPhoto.map(UserPhoto::getUrlBlob).orElse(null);
    }

    private List<String> getUserAccessTypeNames(UUID userId) {
        List<UserAccessType> userAccessTypes = userAccessTypeRepository.findByUser_Id(userId);
        return userAccessTypes.stream()
                .map(userAccessType ->
                        userAccessType.getAccessType().getName())
                .toList();
    }
}
