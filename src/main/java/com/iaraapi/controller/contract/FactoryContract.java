package com.iaraapi.controller.contract;

import com.iaraapi.model.dto.request.FactoryRequest;
import com.iaraapi.model.dto.response.FactoryResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;

import java.util.List;

@Tag(name = "Factory Controller", description = "Endpoints to manage factories")
public interface FactoryContract {

    @Operation(summary = "Create a new factory", description = "Register a new factory in the system")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Factory created successfully",
                    content = @Content(schema = @Schema(implementation = FactoryResponse.class))),
            @ApiResponse(responseCode = "400", description = "Invalid data provided", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
    })
    ResponseEntity<FactoryResponse> createFactory(FactoryRequest factoryRequest);

    @Operation(summary = "List all factories", description = "Retrieve all registered factories")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Request completed successfully",
                    content = @Content(schema = @Schema(implementation = FactoryResponse.class))),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
    })
    ResponseEntity<List<FactoryResponse>> getAllFactories();

    @Operation(summary = "Find factory by ID", description = "Retrieve a specific factory by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Factory found successfully",
                    content = @Content(schema = @Schema(implementation = FactoryResponse.class))),
            @ApiResponse(responseCode = "404", description = "Factory not found", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
    })
    ResponseEntity<FactoryResponse> getFactory(Integer id);

    @Operation(summary = "Update factory", description = "Update an existing factory by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Factory updated successfully",
                    content = @Content(schema = @Schema(implementation = FactoryResponse.class))),
            @ApiResponse(responseCode = "400", description = "Invalid data provided", content = @Content),
            @ApiResponse(responseCode = "404", description = "Factory not found", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
    })
    ResponseEntity<FactoryResponse> updateFactory(Integer id, FactoryRequest factoryRequest);

    @Operation(summary = "Deactivate factory", description = "Deactivate a factory by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Factory deactivated successfully",
                    content = @Content(schema = @Schema(implementation = FactoryResponse.class))),
            @ApiResponse(responseCode = "404", description = "Factory not found", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
    })
    ResponseEntity<FactoryResponse> deactivateFactory(Integer id);

    @Operation(summary = "Reactivate factory", description = "Reactivate a factory by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Factory reactivated successfully",
                    content = @Content(schema = @Schema(implementation = FactoryResponse.class))),
            @ApiResponse(responseCode = "404", description = "Factory not found", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
    })
    ResponseEntity<FactoryResponse> reactivateFactory(Integer id);
}
