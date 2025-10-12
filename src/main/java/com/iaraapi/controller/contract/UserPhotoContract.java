package com.iaraapi.controller.contract;

import com.iaraapi.model.dto.request.UserPhotoRequest;
import com.iaraapi.model.dto.response.UserPhotoResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;

import java.util.List;

@Tag(name = "User Photo Controller", description = "Endpoints to manage user photos")
public interface UserPhotoContract {

    @Operation(summary = "Create a new user photo", description = "Register a new photo for a user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "User photo created successfully",
                    content = @Content(schema = @Schema(implementation = UserPhotoResponse.class))),
            @ApiResponse(responseCode = "400", description = "Invalid data provided", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
    })
    ResponseEntity<UserPhotoResponse> createUserPhoto(UserPhotoRequest userPhotoRequest);

    @Operation(summary = "List all user photos", description = "Retrieve all registered user photos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Request completed successfully",
                    content = @Content(schema = @Schema(implementation = UserPhotoResponse.class))),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
    })
    ResponseEntity<List<UserPhotoResponse>> getAllUserPhotos();

    @Operation(summary = "Find user photo by ID", description = "Retrieve a specific user photo by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User photo found successfully",
                    content = @Content(schema = @Schema(implementation = UserPhotoResponse.class))),
            @ApiResponse(responseCode = "404", description = "User photo not found", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
    })
    ResponseEntity<UserPhotoResponse> getUserPhoto(Integer id);

    @Operation(summary = "Update user photo", description = "Update an existing user photo by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User photo updated successfully",
                    content = @Content(schema = @Schema(implementation = UserPhotoResponse.class))),
            @ApiResponse(responseCode = "400", description = "Invalid data provided", content = @Content),
            @ApiResponse(responseCode = "404", description = "User photo not found", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
    })
    ResponseEntity<UserPhotoResponse> updateUserPhoto(Integer id, UserPhotoRequest userPhotoRequest);
}
