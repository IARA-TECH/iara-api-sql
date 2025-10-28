package com.iaraapi.controller.contract;

import com.iaraapi.model.dto.request.SubscriptionRequest;
import com.iaraapi.model.dto.response.SubscriptionResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;

import java.util.List;

@Tag(name = "Subscription Controller", description = "Endpoints to manage subscriptions")
public interface SubscriptionContract {

    @Operation(summary = "Create a new subscription", description = "Register a new subscription in the system")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Subscription created successfully",
                    content = @Content(schema = @Schema(implementation = SubscriptionResponse.class))),
            @ApiResponse(responseCode = "400", description = "Invalid data provided", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content),
            @ApiResponse(responseCode = "403", description = "Access Denied", content = @Content)
    })
    ResponseEntity<SubscriptionResponse> createSubscription(SubscriptionRequest subscriptionRequest);

    @Operation(summary = "List all subscriptions", description = "Retrieve all registered subscriptions")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Request completed successfully",
                    content = @Content(schema = @Schema(implementation = SubscriptionResponse.class))),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content),
            @ApiResponse(responseCode = "403", description = "Access Denied", content = @Content)
    })
    ResponseEntity<List<SubscriptionResponse>> getAllSubscriptions();

    @Operation(summary = "Find subscription by ID", description = "Retrieve a specific subscription by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Subscription found successfully",
                    content = @Content(schema = @Schema(implementation = SubscriptionResponse.class))),
            @ApiResponse(responseCode = "404", description = "Subscription not found", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content),
            @ApiResponse(responseCode = "403", description = "Access Denied", content = @Content)
    })
    ResponseEntity<SubscriptionResponse> getSubscription(Integer id);

    @Operation(summary = "Update subscription", description = "Update an existing subscription by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Subscription updated successfully",
                    content = @Content(schema = @Schema(implementation = SubscriptionResponse.class))),
            @ApiResponse(responseCode = "400", description = "Invalid data provided", content = @Content),
            @ApiResponse(responseCode = "404", description = "Subscription not found", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content),
            @ApiResponse(responseCode = "403", description = "Access Denied", content = @Content)
    })
    ResponseEntity<SubscriptionResponse> updateSubscription(Integer id, SubscriptionRequest subscriptionRequest);

    @Operation(summary = "Deactivate subscription", description = "Deactivate a subscription by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Subscription deactivated successfully",
                    content = @Content(schema = @Schema(implementation = SubscriptionResponse.class))),
            @ApiResponse(responseCode = "404", description = "Subscription not found", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content),
            @ApiResponse(responseCode = "403", description = "Access Denied", content = @Content)
    })
    ResponseEntity<SubscriptionResponse> deactivateSubscription(Integer id);

    @Operation(summary = "Reactivate subscription", description = "Reactivate a subscription by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Subscription reactivated successfully",
                    content = @Content(schema = @Schema(implementation = SubscriptionResponse.class))),
            @ApiResponse(responseCode = "404", description = "Subscription not found", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content),
            @ApiResponse(responseCode = "403", description = "Access Denied", content = @Content)
    })
    ResponseEntity<SubscriptionResponse> reactivateSubscription(Integer id);
}
