package com.iaraapi.repository;

import com.iaraapi.model.database.UserAccessType;
import com.iaraapi.model.database.UserAccessTypeId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface UserAccessTypeRepository extends JpaRepository<UserAccessType, UserAccessTypeId> {
    List<UserAccessType> findByUser_Id(UUID userId);

    void deleteByUser_IdAndAccessType_Id(UUID userId, Integer accessTypeId);
}
