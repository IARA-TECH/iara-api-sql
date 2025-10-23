package com.iaraapi.controller.contract;

import com.iaraapi.model.dto.response.GenderResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;

@Tag(name = "Health Controller", description = "Endpoint for checking the API status")
public interface HealthContract {
    @Operation(summary = "Checks if the API is running")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "API running successfully",
                    content = @Content(schema = @Schema(implementation = GenderResponse.class))),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
    })
    ResponseEntity<String> health();
}
