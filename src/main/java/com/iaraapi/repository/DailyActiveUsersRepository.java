package com.iaraapi.repository;

import com.iaraapi.model.DailyActiveUsers;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface DailyActiveUsersRepository extends JpaRepository<DailyActiveUsers, Integer> {
    @Procedure(value = "create_daily_active_users")
    @Transactional
    void createDailyActiveUsers(@Param("input_user_account_uuid") UUID userId);
}
