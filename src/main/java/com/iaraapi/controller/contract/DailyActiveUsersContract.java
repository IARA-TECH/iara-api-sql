package com.iaraapi.controller.contract;

import com.iaraapi.model.dto.request.DailyActiveUsersRequest;
import com.iaraapi.model.dto.response.DailyActiveUsersResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;

import java.util.List;

@Tag(name = "Daily Active Users Controller", description = "Endpoints to manage daily active users data")
public interface DailyActiveUsersContract {

    @Operation(summary = "Register daily active users data", description = "Register the number of daily active users")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Daily active users data created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid data provided", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
    })
    ResponseEntity<Void> create(DailyActiveUsersRequest request);

    @Operation(summary = "List all daily active users records", description = "Retrieve all registered daily active users data")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Request completed successfully",
                    content = @Content(schema = @Schema(implementation = DailyActiveUsersResponse.class))),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
    })
    ResponseEntity<List<DailyActiveUsersResponse>> getAll();
}
