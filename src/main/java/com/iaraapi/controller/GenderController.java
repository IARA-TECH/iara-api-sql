package com.iaraapi.controller;

import com.iaraapi.controller.contract.GenderContract;
import com.iaraapi.dto.request.GenderRequest;
import com.iaraapi.dto.response.GenderResponse;
import com.iaraapi.service.GenderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/genders")
@RequiredArgsConstructor
@CrossOrigin("*")
public class GenderController implements GenderContract {
    private final GenderService genderService;

    @PostMapping
    public ResponseEntity<GenderResponse> createGender(@RequestBody @Valid GenderRequest genderRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(genderService.create(genderRequest));
    }

    @GetMapping
    public ResponseEntity<List<GenderResponse>> getAllGender() {
        return ResponseEntity.ok(genderService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<GenderResponse> getGender(@PathVariable Integer id) {
        return ResponseEntity.ok(genderService.getById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<GenderResponse> updateGender(@PathVariable Integer id, @RequestBody @Valid GenderRequest genderRequest) {
        return ResponseEntity.ok(genderService.update(id, genderRequest));
    }

    @PatchMapping("/deactivate/{id}")
    public ResponseEntity<GenderResponse> deactivateGender(@PathVariable Integer id) {
        return ResponseEntity.ok(genderService.deactivateEntity(id));
    }

    @PatchMapping("/reactivate/{id}")
    public ResponseEntity<GenderResponse> reactivateGender(@PathVariable Integer id) {
        return ResponseEntity.ok(genderService.reactivateEntity(id));
    }

}
