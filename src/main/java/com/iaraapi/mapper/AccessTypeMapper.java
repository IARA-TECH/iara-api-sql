package com.iaraapi.mapper;

import com.iaraapi.dto.request.AccessTypeRequest;
import com.iaraapi.dto.response.AccessTypeResponse;
import com.iaraapi.model.AccessType;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface AccessTypeMapper {
    @Mapping(target = "id", ignore = true)
    AccessType toEntity(AccessTypeRequest accessTypeRequest);

    AccessTypeResponse toResponse(AccessType accessType);
}
