package com.iaraapi.model.mapper;

import com.iaraapi.model.dto.request.UserRequest;
import com.iaraapi.model.dto.response.UserResponse;
import com.iaraapi.model.database.Factory;
import com.iaraapi.model.database.Gender;
import com.iaraapi.model.database.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "deactivatedAt", ignore = true)
    @Mapping(source = "request.name", target = "name")
    @Mapping(source = "gender", target = "gender")
    @Mapping(source = "factory", target = "factory")
    User toEntity(UserRequest request, Gender gender, Factory factory);

    @Mappings({
        @Mapping(source = "userManagerName", target = "userManagerName"),
        @Mapping(source = "userPhotoUrl", target = "userPhotoUrl"),
        @Mapping(source = "user.userManagerId", target = "userManagerId"),
        @Mapping(source = "user.gender.name", target = "genderName"),
        @Mapping(source = "user.gender.id", target = "genderId"),
        @Mapping(source = "user.factory.name", target = "factoryName"),
        @Mapping(source = "user.factory.id", target = "factoryId"),
        @Mapping(source = "userAccessTypeNames", target = "userAccessTypeNames")
    })
    UserResponse toResponse(User user, String userManagerName, String userPhotoUrl, List<String> userAccessTypeNames);

}
