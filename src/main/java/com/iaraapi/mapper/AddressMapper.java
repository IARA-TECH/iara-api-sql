package com.iaraapi.mapper;

import com.iaraapi.model.dto.request.AddressRequest;
import com.iaraapi.model.dto.response.AddressResponse;
import com.iaraapi.model.database.Address;
import com.iaraapi.model.database.Factory;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AddressMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(source = "factory", target = "factory")
    Address toEntity(AddressRequest request, Factory factory);

    @Mapping(source = "factory.name", target = "factoryName")
    AddressResponse toResponse(Address address);
}
