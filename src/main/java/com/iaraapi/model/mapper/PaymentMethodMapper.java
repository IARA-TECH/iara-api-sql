package com.iaraapi.model.mapper;

import com.iaraapi.model.dto.request.PaymentMethodRequest;
import com.iaraapi.model.dto.response.PaymentMethodResponse;
import com.iaraapi.model.database.PaymentMethod;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PaymentMethodMapper {
    @Mapping(target = "id", ignore = true)
    PaymentMethod toEntity(PaymentMethodRequest paymentMethodRequest);

    PaymentMethodResponse toResponse(PaymentMethod paymentMethod);
}
