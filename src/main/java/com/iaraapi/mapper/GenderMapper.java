package com.iaraapi.mapper;

import com.iaraapi.dto.request.GenderRequest;
import com.iaraapi.dto.response.GenderResponse;
import com.iaraapi.model.Gender;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper(componentModel = "spring")
public interface GenderMapper {
    @Mapping(target = "id", ignore = true)
    Gender toEntity(GenderRequest genderRequest);

    GenderResponse toResponse(Gender gender);

}
