package com.iaraapi.mapper;

import com.iaraapi.dto.request.UserRequest;
import com.iaraapi.dto.response.UserResponse;
import com.iaraapi.model.Factory;
import com.iaraapi.model.Gender;
import com.iaraapi.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

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
        @Mapping(source = "user.userManagerId", target = "userManagerId"),
        @Mapping(source = "user.gender.name", target = "genderName"),
        @Mapping(source = "user.gender.id", target = "genderId"),
        @Mapping(source = "user.factory.name", target = "factoryName"),
        @Mapping(source = "user.factory.id", target = "factoryId")
    })
    UserResponse toResponse(User user, String userManagerName);

}
