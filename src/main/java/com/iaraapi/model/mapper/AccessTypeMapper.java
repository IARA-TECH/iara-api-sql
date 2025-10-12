package com.iaraapi.model.mapper;

import com.iaraapi.model.dto.request.AccessTypeRequest;
import com.iaraapi.model.dto.response.AccessTypeResponse;
import com.iaraapi.model.database.AccessType;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AccessTypeMapper {
    @Mapping(target = "id", ignore = true)
    AccessType toEntity(AccessTypeRequest accessTypeRequest);

    AccessTypeResponse toResponse(AccessType accessType);
}
