package com.iaraapi.controller;

import com.iaraapi.controller.contract.DailyActiveUsersContract;
import com.iaraapi.dto.request.DailyActiveUsersRequest;
import com.iaraapi.dto.response.DailyActiveUsersResponse;
import com.iaraapi.service.DailyActiveUsersService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/daily-active-users")
public class DailyActiveUsersController implements DailyActiveUsersContract {
    private final DailyActiveUsersService service;

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody @Valid DailyActiveUsersRequest request) {
        service.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping
    public ResponseEntity<List<DailyActiveUsersResponse>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

}
