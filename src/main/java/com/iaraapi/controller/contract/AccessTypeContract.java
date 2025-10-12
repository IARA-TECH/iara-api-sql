package com.iaraapi.controller.contract;

import com.iaraapi.model.dto.request.AccessTypeRequest;
import com.iaraapi.model.dto.response.AccessTypeResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;

import java.util.List;

@Tag(name = "Access Type Controller", description = "Endpoints to manage access types")
public interface AccessTypeContract {

    @Operation(summary = "Create a new access type", description = "Register a new access type in the system")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Access type created successfully",
                    content = @Content(schema = @Schema(implementation = AccessTypeResponse.class))),
            @ApiResponse(responseCode = "400", description = "Invalid data provided", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
    })
    ResponseEntity<AccessTypeResponse> createAccessType(AccessTypeRequest accessTypeRequest);

    @Operation(summary = "List all access types", description = "Retrieve all registered access types")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Request completed successfully",
                    content = @Content(schema = @Schema(implementation = AccessTypeResponse.class))),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
    })
    ResponseEntity<List<AccessTypeResponse>> getAllAccessTypes();

    @Operation(summary = "Find access type by ID", description = "Retrieve a specific access type by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Access type found successfully",
                    content = @Content(schema = @Schema(implementation = AccessTypeResponse.class))),
            @ApiResponse(responseCode = "404", description = "Access type not found", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
    })
    ResponseEntity<AccessTypeResponse> getAccessType(Integer id);

    @Operation(summary = "Update access type", description = "Update an existing access type by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Access type updated successfully",
                    content = @Content(schema = @Schema(implementation = AccessTypeResponse.class))),
            @ApiResponse(responseCode = "400", description = "Invalid data provided", content = @Content),
            @ApiResponse(responseCode = "404", description = "Access type not found", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
    })
    ResponseEntity<AccessTypeResponse> updateAccessType(Integer id, AccessTypeRequest accessTypeRequest);

    @Operation(summary = "Deactivate access type", description = "Deactivate an access type by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Access type deactivated successfully",
                    content = @Content(schema = @Schema(implementation = AccessTypeResponse.class))),
            @ApiResponse(responseCode = "404", description = "Access type not found", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
    })
    ResponseEntity<AccessTypeResponse> deactivateAccessType(Integer id);

    @Operation(summary = "Reactivate access type", description = "Reactivate an access type by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Access type reactivated successfully",
                    content = @Content(schema = @Schema(implementation = AccessTypeResponse.class))),
            @ApiResponse(responseCode = "404", description = "Access type not found", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
    })
    ResponseEntity<AccessTypeResponse> reactivateAccessType(Integer id);
}
