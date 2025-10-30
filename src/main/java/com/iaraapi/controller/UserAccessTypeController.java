package com.iaraapi.controller;

import com.iaraapi.controller.contract.UserAccessTypeContract;
import com.iaraapi.model.dto.request.UserAccessTypeRequest;
import com.iaraapi.model.dto.response.UserAccessTypeResponse;
import com.iaraapi.service.UserAccessTypeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("api/v1/user-access-types")
@CrossOrigin("*")
@RequiredArgsConstructor
public class UserAccessTypeController implements UserAccessTypeContract {

    private final UserAccessTypeService userAccessTypeService;

    @PostMapping
    @PreAuthorize("hasRole('Administrador')")
    public ResponseEntity<UserAccessTypeResponse> create(@RequestBody UserAccessTypeRequest request) {
        log.info("[UserAccessTypeController] [create] Creating user-access-type relation");
        return ResponseEntity.ok(userAccessTypeService.create(request));
    }

    @GetMapping("/{userId}")
    @PreAuthorize("hasAnyRole('Administrador', 'Visualizador')")
    public ResponseEntity<List<UserAccessTypeResponse>> getAllByUserId(@PathVariable UUID userId) {
        log.info("[UserAccessTypeController] [getAllByUserId] Listing all active access types for user {}", userId);
        return ResponseEntity.ok(userAccessTypeService.getAllByUserId(userId));
    }

    @DeleteMapping("access-type/{accessTypeId}/user/{userId}")
    @PreAuthorize("hasRole('Administrador')")
    public ResponseEntity<String> delete(@PathVariable UUID userId,  @PathVariable Integer accessTypeId) {
        log.info("[UserAccessTypeController] [delete] Deleting user access type");
        return ResponseEntity.ok(userAccessTypeService.deleteByUserId(userId, accessTypeId));
    }
}
