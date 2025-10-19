package com.iaraapi.controller.contract;

import com.iaraapi.model.redis.UserAccessRedis;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.UUID;

@Tag(name = "Redis Cache Controller", description = "Endpoints to manage user access cache in Redis")
public interface RedisCacheContract {

    @Operation(summary = "Cache user access types", description = "Save the access types of a specific user in Redis")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "User access cached successfully"),
            @ApiResponse(responseCode = "404", description = "User not found", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
    })
    ResponseEntity<String> save(UUID userId);

    @Operation(summary = "List cached user access types", description = "Retrieve the cached access types of a specific user from Redis")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cached access types retrieved successfully",
                    content = @Content(schema = @Schema(implementation = UserAccessRedis.class))),
            @ApiResponse(responseCode = "404", description = "User not found", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
    })
    ResponseEntity<List<UserAccessRedis>> list(UUID userId);
}
