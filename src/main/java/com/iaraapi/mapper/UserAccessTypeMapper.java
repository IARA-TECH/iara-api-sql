package com.iaraapi.mapper;

import com.iaraapi.dto.request.UserAccessTypeRequest;
import com.iaraapi.dto.response.UserAccessTypeResponse;
import com.iaraapi.model.UserAccessType;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserAccessTypeMapper {
    @Mapping(source = "request.userId", target = "user.id")
    @Mapping(source = "request.accessTypeId", target = "accessType.id")
    UserAccessType toEntity(UserAccessTypeRequest request);

    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "accessType.id", target = "accessTypeId")
    @Mapping(source = "accessType.name", target = "accessTypeName")
    UserAccessTypeResponse toResponse(UserAccessType userAccessType);
}
