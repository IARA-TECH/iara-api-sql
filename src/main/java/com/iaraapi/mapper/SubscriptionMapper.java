package com.iaraapi.mapper;

import com.iaraapi.dto.request.SubscriptionRequest;
import com.iaraapi.dto.response.SubscriptionResponse;
import com.iaraapi.model.Subscription;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface SubscriptionMapper {

    @Mapping(target = "id", ignore = true)
    Subscription toEntity(SubscriptionRequest subscription);

    SubscriptionResponse toResponse(Subscription subscription);
}
