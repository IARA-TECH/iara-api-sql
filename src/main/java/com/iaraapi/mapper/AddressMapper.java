package com.iaraapi.mapper;

import com.iaraapi.dto.request.AddressRequest;
import com.iaraapi.dto.response.AddressResponse;
import com.iaraapi.model.Address;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AddressMapper {
    @Mapping(target = "id", ignore = true)
    Address toEntity(AddressRequest  request);

    AddressResponse toResponse(Address address);
}
