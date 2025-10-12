package com.iaraapi.model.mapper;

import com.iaraapi.model.dto.request.UserPhotoRequest;
import com.iaraapi.model.dto.response.UserPhotoResponse;
import com.iaraapi.model.database.User;
import com.iaraapi.model.database.UserPhoto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserPhotoMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(source = "user", target = "user")
    UserPhoto toEntity(UserPhotoRequest request, User user);

    @Mapping(source = "user.id", target = "userId")
    UserPhotoResponse toResponse(UserPhoto userPhoto);
}
