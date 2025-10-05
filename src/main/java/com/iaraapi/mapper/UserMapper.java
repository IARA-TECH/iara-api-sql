package com.iaraapi.mapper;

import com.iaraapi.dto.request.UserRequest;
import com.iaraapi.dto.response.UserResponse;
import com.iaraapi.model.Factory;
import com.iaraapi.model.Gender;
import com.iaraapi.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper
public interface UserMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(source = "gender", target = "gender")
    @Mapping(source = "factory", target = "factory")
    User toEntity(UserRequest request, Gender gender, Factory factory);

    @Mappings({
        @Mapping(source = "userManagerName", target = "userManagerName"),
        @Mapping(source = "user.userManagerId", target = "userManagerId"),
        @Mapping(source = "gender.name", target = "genderName"),
        @Mapping(source = "gender.id", target = "genderId"),
        @Mapping(source = "factory.name", target = "factoryName"),
        @Mapping(source = "factory.id", target = "factoryId")
    })
    UserResponse toResponse(User user, String userManagerName);

}
