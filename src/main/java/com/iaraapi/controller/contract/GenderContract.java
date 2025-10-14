package com.iaraapi.controller.contract;

import com.iaraapi.model.dto.request.GenderRequest;
import com.iaraapi.model.dto.response.GenderResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;

import java.util.List;

@Tag(name = "Gender Controller", description = "Endpoints to manage genders")
public interface GenderContract {

    @Operation(summary = "Create a new gender", description = "Register a new gender in the system")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Gender created successfully",
                    content = @Content(schema = @Schema(implementation = GenderResponse.class))),
            @ApiResponse(responseCode = "400", description = "Invalid data provided", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
    })
    ResponseEntity<GenderResponse> createGender(GenderRequest genderRequest);

    @Operation(summary = "List all genders", description = "Retrieve all registered genders")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Request completed successfully",
                    content = @Content(schema = @Schema(implementation = GenderResponse.class))),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
    })
    ResponseEntity<List<GenderResponse>> getAllGender();

    @Operation(summary = "Find gender by ID", description = "Retrieve a specific gender by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Gender found successfully",
                    content = @Content(schema = @Schema(implementation = GenderResponse.class))),
            @ApiResponse(responseCode = "404", description = "Gender not found", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
    })
    ResponseEntity<GenderResponse> getGender(Integer id);

    @Operation(summary = "Update gender", description = "Update an existing gender by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Gender updated successfully",
                    content = @Content(schema = @Schema(implementation = GenderResponse.class))),
            @ApiResponse(responseCode = "400", description = "Invalid data provided", content = @Content),
            @ApiResponse(responseCode = "404", description = "Gender not found", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
    })
    ResponseEntity<GenderResponse> updateGender(Integer id, GenderRequest genderRequest);

    @Operation(summary = "Deactivate gender", description = "Deactivate a gender by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Gender deactivated successfully",
                    content = @Content(schema = @Schema(implementation = GenderResponse.class))),
            @ApiResponse(responseCode = "404", description = "Gender not found", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
    })
    ResponseEntity<GenderResponse> deactivateGender(Integer id);

    @Operation(summary = "Reactivate gender", description = "Reactivate a gender by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Gender reactivated successfully",
                    content = @Content(schema = @Schema(implementation = GenderResponse.class))),
            @ApiResponse(responseCode = "404", description = "Gender not found", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
    })
    ResponseEntity<GenderResponse> reactivateGender(Integer id);
}
