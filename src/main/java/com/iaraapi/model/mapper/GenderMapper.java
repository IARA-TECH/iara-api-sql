package com.iaraapi.model.mapper;

import com.iaraapi.model.dto.request.GenderRequest;
import com.iaraapi.model.dto.response.GenderResponse;
import com.iaraapi.model.database.Gender;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper(componentModel = "spring")
public interface GenderMapper {
    @Mapping(target = "id", ignore = true)
    Gender toEntity(GenderRequest genderRequest);

    GenderResponse toResponse(Gender gender);

}
