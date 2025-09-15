package com.iaraapi.mapper;

import com.iaraapi.dto.response.PaymentMethodResponse;
import com.iaraapi.model.PaymentMethod;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PaymentMethodMapper {
    @Mapping(target = "id", ignore = true)
    PaymentMethod toEntity(PaymentMethod paymentMethod);

    PaymentMethodResponse toResponse(PaymentMethod paymentMethod);
}
