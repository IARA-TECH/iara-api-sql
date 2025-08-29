package com.iaraapi.repository;

import com.iaraapi.model.UserPhoto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserPhotoRepository extends JpaRepository<UserPhoto,Long> {
}
