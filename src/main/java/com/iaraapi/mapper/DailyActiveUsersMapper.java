package com.iaraapi.mapper;

import com.iaraapi.model.dto.request.DailyActiveUsersRequest;
import com.iaraapi.model.dto.response.DailyActiveUsersResponse;
import com.iaraapi.model.database.DailyActiveUsers;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface DailyActiveUsersMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(source = "userId", target = "user.id")
    DailyActiveUsers toEntity(DailyActiveUsersRequest request);

    @Mapping(source = "user.id", target = "userId")
    DailyActiveUsersResponse toResponse(DailyActiveUsers entity);
}
