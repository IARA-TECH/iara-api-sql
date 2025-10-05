package com.iaraapi.mapper;

import com.iaraapi.dto.request.UserPhotoRequest;
import com.iaraapi.dto.response.UserPhotoResponse;
import com.iaraapi.model.User;
import com.iaraapi.model.UserPhoto;
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
