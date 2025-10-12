package com.iaraapi.controller;

import com.iaraapi.controller.contract.UserAccessTypeContract;
import com.iaraapi.model.dto.request.UserAccessTypeRequest;
import com.iaraapi.model.dto.response.UserAccessTypeResponse;
import com.iaraapi.service.UserAccessTypeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("api/v1/user-access-type")
@CrossOrigin("*")
@RequiredArgsConstructor
public class UserAccessTypeController implements UserAccessTypeContract {

    private final UserAccessTypeService userAccessTypeService;

    @PostMapping
    public ResponseEntity<UserAccessTypeResponse> create(@RequestBody UserAccessTypeRequest request) {
        log.info("[UserAccessTypeController] [create] Creating user-access-type relation");
        return ResponseEntity.ok(userAccessTypeService.create(request));
    }

    @GetMapping("/{userId}")
    public ResponseEntity<List<UserAccessTypeResponse>> getAllByUserId(@PathVariable UUID userId) {
        log.info("[UserAccessTypeController] [getAllByUserId] Listing all active access types for user {}", userId);
        return ResponseEntity.ok(userAccessTypeService.getAllByUserId(userId));
    }


    @PatchMapping("/{userId}/deactivate/{accessTypeId}")
    public ResponseEntity<UserAccessTypeResponse> deactivate(
            @PathVariable UUID userId,
            @PathVariable Integer accessTypeId) {
        log.info("[UserAccessTypeController] [deactivate] Deactivating relation user={} accessType={}", userId, accessTypeId);
        return ResponseEntity.ok(userAccessTypeService.deactivate(userId, accessTypeId));
    }

    @PatchMapping("/{userId}/reactivate/{accessTypeId}")
    public ResponseEntity<UserAccessTypeResponse> reactivate(
            @PathVariable UUID userId,
            @PathVariable Integer accessTypeId) {
        log.info("[UserAccessTypeController] [reactivate] Reactivating relation user={} accessType={}", userId, accessTypeId);
        return ResponseEntity.ok(userAccessTypeService.reactivate(userId, accessTypeId));
    }
}
