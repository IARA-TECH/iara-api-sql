package com.iaraapi.controller.contract;

import com.iaraapi.model.dto.request.PaymentMethodRequest;
import com.iaraapi.model.dto.response.PaymentMethodResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;

import java.util.List;

@Tag(name = "Payment Method Controller", description = "Endpoints to manage payment methods")
public interface PaymentMethodContract {

    @Operation(summary = "Create a new payment method", description = "Register a new payment method in the system")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Payment method created successfully",
                    content = @Content(schema = @Schema(implementation = PaymentMethodResponse.class))),
            @ApiResponse(responseCode = "400", description = "Invalid data provided", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
    })
    ResponseEntity<PaymentMethodResponse> createPaymentMethod(PaymentMethodRequest paymentMethodRequest);

    @Operation(summary = "List all payment methods", description = "Retrieve all registered payment methods")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Request completed successfully",
                    content = @Content(schema = @Schema(implementation = PaymentMethodResponse.class))),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
    })
    ResponseEntity<List<PaymentMethodResponse>> getAllPaymentMethods();

    @Operation(summary = "Find payment method by ID", description = "Retrieve a specific payment method by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Payment method found successfully",
                    content = @Content(schema = @Schema(implementation = PaymentMethodResponse.class))),
            @ApiResponse(responseCode = "404", description = "Payment method not found", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
    })
    ResponseEntity<PaymentMethodResponse> getPaymentMethod(Integer id);

    @Operation(summary = "Update payment method", description = "Update an existing payment method by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Payment method updated successfully",
                    content = @Content(schema = @Schema(implementation = PaymentMethodResponse.class))),
            @ApiResponse(responseCode = "400", description = "Invalid data provided", content = @Content),
            @ApiResponse(responseCode = "404", description = "Payment method not found", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
    })
    ResponseEntity<PaymentMethodResponse> updatePaymentMethod(Integer id, PaymentMethodRequest paymentMethodRequest);

    @Operation(summary = "Deactivate payment method", description = "Deactivate a payment method by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Payment method deactivated successfully",
                    content = @Content(schema = @Schema(implementation = PaymentMethodResponse.class))),
            @ApiResponse(responseCode = "404", description = "Payment method not found", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
    })
    ResponseEntity<PaymentMethodResponse> deactivatePaymentMethod(Integer id);

    @Operation(summary = "Reactivate payment method", description = "Reactivate a payment method by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Payment method reactivated successfully",
                    content = @Content(schema = @Schema(implementation = PaymentMethodResponse.class))),
            @ApiResponse(responseCode = "404", description = "Payment method not found", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
    })
    ResponseEntity<PaymentMethodResponse> reactivatePaymentMethod(Integer id);
}
