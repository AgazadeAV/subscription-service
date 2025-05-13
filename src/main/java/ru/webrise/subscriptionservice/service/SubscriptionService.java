package ru.webrise.subscriptionservice.service;

import ru.webrise.subscriptionservice.dto.CreateSubscriptionRequest;
import ru.webrise.subscriptionservice.dto.SubscriptionDto;

import java.util.List;
import java.util.UUID;

public interface SubscriptionService {
    SubscriptionDto addSubscription(UUID userId, CreateSubscriptionRequest request);

    List<SubscriptionDto> getUserSubscriptions(UUID userId);

    void deleteSubscription(UUID userId, UUID subscriptionId);

    List<SubscriptionDto> getTopSubscriptions();
}
