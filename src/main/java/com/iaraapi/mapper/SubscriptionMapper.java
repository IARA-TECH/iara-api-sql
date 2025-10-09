package com.iaraapi.mapper;

import com.iaraapi.model.dto.request.SubscriptionRequest;
import com.iaraapi.model.dto.response.SubscriptionResponse;
import com.iaraapi.model.database.Subscription;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface SubscriptionMapper {

    @Mapping(target = "id", ignore = true)
    Subscription toEntity(SubscriptionRequest subscription);

    SubscriptionResponse toResponse(Subscription subscription);
}
