package ru.webrise.subscriptionservice.mapper;

import org.mapstruct.Mapper;
import ru.webrise.subscriptionservice.dto.CreateSubscriptionRequest;
import ru.webrise.subscriptionservice.dto.SubscriptionDto;
import ru.webrise.subscriptionservice.model.Subscription;

@Mapper(componentModel = "spring")
public interface SubscriptionMapper {

    Subscription toEntity(CreateSubscriptionRequest request);

    SubscriptionDto toDto(Subscription subscription);
}
