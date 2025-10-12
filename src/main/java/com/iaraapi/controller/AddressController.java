package com.iaraapi.controller;

import com.iaraapi.model.dto.request.AddressRequest;
import com.iaraapi.model.dto.response.AddressResponse;
import com.iaraapi.service.AddressService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/addresses")
@RequiredArgsConstructor
@CrossOrigin("*")
public class AddressController {
    private final AddressService addressService;

    @PostMapping
    public ResponseEntity<AddressResponse> createAddress(@RequestBody @Valid AddressRequest addressRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(addressService.create(addressRequest));
    }

    @GetMapping
    public ResponseEntity<List<AddressResponse>> getAllAddress() {
        return ResponseEntity.ok(addressService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AddressResponse> getAddressType(@PathVariable Integer id) {
        return ResponseEntity.ok(addressService.getById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AddressResponse> updateAddressType(@PathVariable Integer id, @RequestBody @Valid AddressRequest addressRequest) {
        return ResponseEntity.ok(addressService.update(id, addressRequest));
    }

    @PatchMapping("/deactivate/{id}")
    public ResponseEntity<AddressResponse> deactivateAddressType(@PathVariable Integer id) {
        return ResponseEntity.ok(addressService.deactivateEntity(id));
    }

    @PatchMapping("/reactivate/{id}")
    public ResponseEntity<AddressResponse> reactivateAddressType(@PathVariable Integer id) {
        return ResponseEntity.ok(addressService.reactivateEntity(id));
    }

}
