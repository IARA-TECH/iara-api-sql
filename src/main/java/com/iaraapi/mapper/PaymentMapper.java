package com.iaraapi.mapper;

import com.iaraapi.dto.request.PaymentRequest;
import com.iaraapi.dto.response.PaymentResponse;
import com.iaraapi.model.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PaymentMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(source = "subscription", target = "subscription")
    @Mapping(source = "paymentMethod", target = "paymentMethod")
    @Mapping(source = "user", target = "user")
    Payment toEntity(PaymentRequest request, Subscription subscription,
                     PaymentMethod paymentMethod, User user);


    @Mapping(source = "subscription.name", target = "subscriptionName")
    @Mapping(source = "paymentMethod.name", target = "paymentMethodName")
    @Mapping(source = "user.name", target = "userName")
    PaymentResponse toResponse(Payment payment);
}
