package com.iaraapi.repository;

import com.iaraapi.model.dto.response.UserFactoryResponse;
import com.iaraapi.model.database.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
    @Procedure(value = "create_user_account")
    void createUserAccount(
            @Param("input_name") String name,
            @Param("input_email") String email,
            @Param("input_password") String password,
            @Param("input_date_of_birth") Date dateOfBirth,
            @Param("input_gender_id") Integer genderId,
            @Param("input_factory_id") Integer factoryId,
            @Param("input_user_manager_uuid") UUID userManagerUuid
    );



    @Query(value = """
        SELECT 
            u.pk_uuid       AS id,
            u.created_at    AS createdAt,
            u.changed_at    AS changedAt,
            u.deactivated_at AS deactivatedAt,
            u.name          AS name,
            u.email         AS email,
            u.date_of_birth AS dateOfBirth,
            u.gender_id     AS genderId,
            u.gender        AS gender
        FROM get_user_accounts_by_factory(:factoryId) u
        """,
            nativeQuery = true)
    List<UserFactoryResponse> findUserAccountsByFactory(@Param("factoryId") Integer factoryId);

    Optional<User> findByEmailIgnoreCase(String email);

    Optional<List<User>> findByNameContainsIgnoreCase(String name);
}
