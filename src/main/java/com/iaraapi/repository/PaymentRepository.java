package com.iaraapi.repository;

import com.iaraapi.model.database.Payment;
import com.iaraapi.model.dto.response.FactoryActivePayment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Integer> {
    @Procedure(procedureName = "create_payment")
    void createPayment(
            @Param("input_user_account_uuid") UUID userUuid,
            @Param("input_subscription_id") Integer subscriptionId,
            @Param("input_payment_method_id") Integer paymentMethodId
    );

    @Query(
            value = "SELECT * FROM get_factory_active_payment(:factoryId)",
            nativeQuery = true
    )
    FactoryActivePayment getFactoryActivePayment(@Param("factoryId") Integer factoryId);

    Optional<Payment> findTopByUser_IdOrderByPaidAtDesc(UUID userUuid);
}
