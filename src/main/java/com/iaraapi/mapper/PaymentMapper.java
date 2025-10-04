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
    @Mapping(source = "factory", target = "factory")
    Payment toEntity(PaymentRequest request, Subscription subscription, PaymentMethod paymentMethod,
                     User user, Factory factory);


    @Mapping(source = "subscription.id", target = "subscriptionId")
    @Mapping(source = "paymentMethod.name", target = "paymentMethodName")
    @Mapping(source = "user.name", target = "userName")
    @Mapping(source = "factory.name", target = "factoryName")
    PaymentResponse toResponse(Payment payment);
}
