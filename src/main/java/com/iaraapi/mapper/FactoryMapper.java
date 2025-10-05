package com.iaraapi.mapper;

import com.iaraapi.dto.request.FactoryRequest;
import com.iaraapi.dto.response.FactoryResponse;
import com.iaraapi.model.Factory;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface FactoryMapper {
    @Mapping(target = "id", ignore = true)
    Factory toEntity(FactoryRequest request);

    FactoryResponse toResponse(Factory factory);
}
