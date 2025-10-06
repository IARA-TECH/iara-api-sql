package com.iaraapi.repository;

import com.iaraapi.model.User;
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


    @Query(value = "SELECT * FROM get_user_accounts_by_factory(:factoryId)", nativeQuery = true)
    List<User> findUserAccountsByFactory(@Param("factoryId") Integer factoryId);


    Optional<User> findByEmail(String email);

    Optional<User> findByName(String name);
}
