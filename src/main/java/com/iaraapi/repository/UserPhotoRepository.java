package com.iaraapi.repository;

import com.iaraapi.model.database.UserPhoto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserPhotoRepository extends JpaRepository<UserPhoto,Integer> {
    @Procedure(value  = "create_user_account_photo")
    void createUserAccountPhoto(
            @Param("input_user_account_uuid") UUID userAccountUuid,
            @Param("input_url_blob") String urlBlob
    );

    Optional<UserPhoto> findByUser_id(UUID userAccountUuid);
}
