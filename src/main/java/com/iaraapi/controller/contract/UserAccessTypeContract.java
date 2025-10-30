package com.iaraapi.controller.contract;

import com.iaraapi.model.dto.request.UserAccessTypeRequest;
import com.iaraapi.model.dto.response.UserAccessTypeResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.UUID;

@Tag(name = "User Access Type Controller", description = "Endpoints to manage the relationship between Users and Access Types")
public interface UserAccessTypeContract {

    @Operation(summary = "Create user-access-type relation", description = "Associate an access type to a specific user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Relation created successfully",
                    content = @Content(schema = @Schema(implementation = UserAccessTypeResponse.class))),
            @ApiResponse(responseCode = "400", description = "Invalid data provided", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content),
            @ApiResponse(responseCode = "404", description = "Data not found", content = @Content),
            @ApiResponse(responseCode = "403", description = "Access Denied", content = @Content)
    })
    ResponseEntity<UserAccessTypeResponse> create(UserAccessTypeRequest request);

    @Operation(summary = "List all user access types", description = "Retrieve all active access types associated with a specific user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Request completed successfully",
                    content = @Content(schema = @Schema(implementation = UserAccessTypeResponse.class))),
            @ApiResponse(responseCode = "404", description = "User not found", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content),
            @ApiResponse(responseCode = "403", description = "Access Denied", content = @Content)
    })
    ResponseEntity<List<UserAccessTypeResponse>> getAllByUserId(UUID userId);

    @Operation(summary = "List all user access types", description = "Retrieve all active access types associated with a specific user.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Request completed successfully",
                    content = @Content(schema = @Schema(implementation = UserAccessTypeResponse.class))),
            @ApiResponse(responseCode = "404", description = "User not found", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content),
            @ApiResponse(responseCode = "403", description = "Access Denied", content = @Content)
    })
    public ResponseEntity<String> delete(@PathVariable UUID userId, @PathVariable Integer accessTypeId);
}
