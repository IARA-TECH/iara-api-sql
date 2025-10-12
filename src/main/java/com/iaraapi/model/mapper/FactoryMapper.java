package com.iaraapi.model.mapper;

import com.iaraapi.model.dto.request.FactoryRequest;
import com.iaraapi.model.dto.response.FactoryResponse;
import com.iaraapi.model.database.Factory;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface FactoryMapper {
    @Mapping(target = "id", ignore = true)
    Factory toEntity(FactoryRequest request);

    FactoryResponse toResponse(Factory factory);
}
