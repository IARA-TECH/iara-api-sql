package com.iaraapi.controller.contract;

import com.iaraapi.model.dto.request.EmailRequest;
import com.iaraapi.model.dto.request.NameRequest;
import com.iaraapi.model.dto.request.UserRequest;
import com.iaraapi.model.dto.response.UserResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.UUID;

@Tag(name = "User Controller", description = "Endpoints to manage users")
public interface UserContract {

    @Operation(summary = "Create a new user", description = "Register a new user in the system")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "User created successfully",
                    content = @Content(schema = @Schema(implementation = UserResponse.class))),
            @ApiResponse(responseCode = "400", description = "Invalid data provided", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
    })
    ResponseEntity<UserResponse> createUser(UserRequest userRequest);

    @Operation(summary = "List all users", description = "Retrieve all registered users")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Request completed successfully",
                    content = @Content(schema = @Schema(implementation = UserResponse.class))),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
    })
    ResponseEntity<List<UserResponse>> getAllUsers();

    @Operation(summary = "Find user by ID", description = "Retrieve a specific user by their ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User found successfully",
                    content = @Content(schema = @Schema(implementation = UserResponse.class))),
            @ApiResponse(responseCode = "404", description = "User not found", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
    })
    ResponseEntity<UserResponse> getUserById(UUID id);

    @Operation(summary = "Find user by email", description = "Retrieve a user by their email")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User found successfully",
                    content = @Content(schema = @Schema(implementation = UserResponse.class))),
            @ApiResponse(responseCode = "404", description = "User not found", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
    })
    ResponseEntity<UserResponse> getUserByEmail(EmailRequest request);

    @Operation(summary = "Find users by name", description = "Retrieve users matching a specific name")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Request completed successfully",
                    content = @Content(schema = @Schema(implementation = UserResponse.class))),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
    })
    ResponseEntity<List<UserResponse>> getUserByName(NameRequest request);

    @Operation(summary = "Get users by factory", description = "Retrieve all users associated with a specific factory")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Request completed successfully",
                    content = @Content(schema = @Schema(implementation = UserResponse.class))),
            @ApiResponse(responseCode = "404", description = "Factory not found", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
    })
    ResponseEntity<List<UserResponse>> getUsersByFactory(Integer factoryId);

    @Operation(summary = "Update user", description = "Update an existing user by their ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User updated successfully",
                    content = @Content(schema = @Schema(implementation = UserResponse.class))),
            @ApiResponse(responseCode = "400", description = "Invalid data provided", content = @Content),
            @ApiResponse(responseCode = "404", description = "User not found", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
    })
    ResponseEntity<UserResponse> updateUser(UUID id, UserRequest userRequest);

    @Operation(summary = "Deactivate user", description = "Deactivate a user by their ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User deactivated successfully",
                    content = @Content(schema = @Schema(implementation = UserResponse.class))),
            @ApiResponse(responseCode = "404", description = "User not found", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
    })
    ResponseEntity<UserResponse> deactivateUser(UUID id);

    @Operation(summary = "Reactivate user", description = "Reactivate a user by their ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User reactivated successfully",
                    content = @Content(schema = @Schema(implementation = UserResponse.class))),
            @ApiResponse(responseCode = "404", description = "User not found", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
    })
    ResponseEntity<UserResponse> reactivateUser(UUID id);
}
