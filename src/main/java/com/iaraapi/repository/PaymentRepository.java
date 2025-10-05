package com.iaraapi.repository;

import com.iaraapi.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Integer> {
    @Query(value = "CALL create_payment(:userUuid, :subscriptionId, :paymentMethodId)", nativeQuery = true)
    void createPayment(
            @Param("userUuid") UUID userUuid,
            @Param("subscriptionId") Integer subscriptionId,
            @Param("paymentMethodId") Integer paymentMethodId
    );

    Optional<Payment> findTopByUser_IdOrderByPaidAtDesc(UUID userUuid);
}
