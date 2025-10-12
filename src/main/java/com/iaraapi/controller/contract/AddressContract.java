package com.iaraapi.controller.contract;

import com.iaraapi.dto.request.AddressRequest;
import com.iaraapi.dto.response.AddressResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;

import java.util.List;

@Tag(name = "Address Controller", description = "Endpoints to manage addresses")
public interface AddressContract {

    @Operation(summary = "Create a new address", description = "Register a new address in the system")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Address created successfully",
                    content = @Content(schema = @Schema(implementation = AddressResponse.class))),
            @ApiResponse(responseCode = "400", description = "Invalid data provided", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
    })
    ResponseEntity<AddressResponse> createAddress(AddressRequest addressRequest);

    @Operation(summary = "List all addresses", description = "Retrieve all registered addresses")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Request completed successfully",
                    content = @Content(schema = @Schema(implementation = AddressResponse.class))),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
    })
    ResponseEntity<List<AddressResponse>> getAllAddress();

    @Operation(summary = "Find address by ID", description = "Retrieve a specific address by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Address found successfully",
                    content = @Content(schema = @Schema(implementation = AddressResponse.class))),
            @ApiResponse(responseCode = "404", description = "Address not found", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
    })
    ResponseEntity<AddressResponse> getAddress(Integer id);

    @Operation(summary = "Update address", description = "Update an existing address by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Address updated successfully",
                    content = @Content(schema = @Schema(implementation = AddressResponse.class))),
            @ApiResponse(responseCode = "400", description = "Invalid data provided", content = @Content),
            @ApiResponse(responseCode = "404", description = "Address not found", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
    })
    ResponseEntity<AddressResponse> updateAddress(Integer id, AddressRequest addressRequest);

    @Operation(summary = "Deactivate address", description = "Deactivate an address by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Address deactivated successfully",
                    content = @Content(schema = @Schema(implementation = AddressResponse.class))),
            @ApiResponse(responseCode = "404", description = "Address not found", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
    })
    ResponseEntity<AddressResponse> deactivateAddress(Integer id);

    @Operation(summary = "Reactivate address", description = "Reactivate an address by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Address reactivated successfully",
                    content = @Content(schema = @Schema(implementation = AddressResponse.class))),
            @ApiResponse(responseCode = "404", description = "Address not found", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
    })
    ResponseEntity<AddressResponse> reactivateAddress(Integer id);
}
