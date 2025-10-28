package com.iaraapi.model.mapper;

import com.iaraapi.model.dto.request.PaymentRequest;
import com.iaraapi.model.dto.response.PaymentResponse;
import com.iaraapi.model.database.Payment;
import com.iaraapi.model.database.PaymentMethod;
import com.iaraapi.model.database.Subscription;
import com.iaraapi.model.database.User;
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
    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "payment.id", target = "id")
    PaymentResponse toResponse(Payment payment);
}
