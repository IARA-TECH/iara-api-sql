package com.iaraapi.controller.redis;

import com.iaraapi.model.redis.UserAccessRedis;
import com.iaraapi.service.redis.UserAccessRedisService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/cache")
@RequiredArgsConstructor
public class RedisCacheController implements RedisCacheContract {

    private final UserAccessRedisService userAccessRedisService;

    @PostMapping("/save/{userId}")
    public ResponseEntity<String> save(@PathVariable UUID userId) {
        userAccessRedisService.cacheUserAccess(userId);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body("User access type cached successfully");
    }

    @GetMapping("/list/{userId}")
    public ResponseEntity<List<UserAccessRedis>> list(@PathVariable UUID userId) {
        return ResponseEntity.ok(userAccessRedisService.getCachedAccess(userId));
    }
}

