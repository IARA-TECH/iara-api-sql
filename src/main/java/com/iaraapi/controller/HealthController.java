package com.iaraapi.controller;

import com.iaraapi.controller.contract.HealthContract;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/health")
public class HealthController implements HealthContract {
    @GetMapping
    public ResponseEntity<String> health() {
        return ResponseEntity.ok( "OK");
    }
}
