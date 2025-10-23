package com.iaraapi.controller;

import com.iaraapi.controller.contract.AddressContract;
import com.iaraapi.model.dto.request.AddressRequest;
import com.iaraapi.model.dto.response.AddressResponse;
import com.iaraapi.service.AddressService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/addresses")
@RequiredArgsConstructor
@CrossOrigin("*")
public class AddressController implements AddressContract {
    private final AddressService addressService;

    @PostMapping
    @PreAuthorize("hasRole('Administrador')")
    public ResponseEntity<AddressResponse> createAddress(@RequestBody @Valid AddressRequest addressRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(addressService.create(addressRequest));
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('Administrador', 'Visualizador')")
    public ResponseEntity<List<AddressResponse>> getAllAddress() {
        return ResponseEntity.ok(addressService.getAll());
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('Administrador', 'Visualizador')")
    public ResponseEntity<AddressResponse> getAddress(@PathVariable Integer id) {
        return ResponseEntity.ok(addressService.getById(id));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('Administrador')")
    public ResponseEntity<AddressResponse> updateAddress(@PathVariable Integer id, @RequestBody @Valid AddressRequest addressRequest) {
        return ResponseEntity.ok(addressService.update(id, addressRequest));
    }

    @PatchMapping("/deactivate/{id}")
    @PreAuthorize("hasRole('Administrador')")
    public ResponseEntity<AddressResponse> deactivateAddress(@PathVariable Integer id) {
        return ResponseEntity.ok(addressService.deactivateEntity(id));
    }

    @PatchMapping("/reactivate/{id}")
    @PreAuthorize("hasRole('Administrador')")
    public ResponseEntity<AddressResponse> reactivateAddress(@PathVariable Integer id) {
        return ResponseEntity.ok(addressService.reactivateEntity(id));
    }
}
