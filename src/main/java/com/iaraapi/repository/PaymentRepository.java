package com.iaraapi.repository;

import com.iaraapi.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {
    @Query(value = "CALL create_payment(:userUuid, :subscriptionId, :paymentMethodId)", nativeQuery = true)
    void createPayment(
            @Param("userUuid") UUID userUuid,
            @Param("subscriptionId") Long subscriptionId,
            @Param("paymentMethodId") Long paymentMethodId
    );

    Optional<Payment> findTopByUserAccountUuidOrderByPaidAtDesc(UUID userUuid);
}
