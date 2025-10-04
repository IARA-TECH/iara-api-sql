package com.iaraapi.mapper;

import com.iaraapi.dto.request.AddressRequest;
import com.iaraapi.dto.response.AddressResponse;
import com.iaraapi.model.Address;
import com.iaraapi.model.Factory;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AddressMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(source = "factory.id", target = "factory.id")
    @Mapping(source = "factory.name", target = "factory.name")
    Address toEntity(AddressRequest request, Factory factory);

    @Mapping(source = "factory.name", target = "factoryName")
    AddressResponse toResponse(Address address);
}
