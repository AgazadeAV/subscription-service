package ru.webrise.subscriptionservice.mapper;

import org.mapstruct.Mapper;
import ru.webrise.subscriptionservice.dto.CreateSubscriptionRequest;
import ru.webrise.subscriptionservice.dto.SubscriptionDto;
import ru.webrise.subscriptionservice.dto.TopSubscriptionDto;
import ru.webrise.subscriptionservice.dto.projection.TopServiceProjection;
import ru.webrise.subscriptionservice.model.Subscription;

@Mapper(componentModel = "spring")
public interface SubscriptionMapper {

    Subscription mapToSubscription(CreateSubscriptionRequest request);

    SubscriptionDto mapToSubscriptionDto(Subscription subscription);

    default TopSubscriptionDto mapToTopSubscriptionDto(TopServiceProjection projection, int position, long totalCount) {
        long count = projection.getCount();
        double percentage = (count * 100.0) / totalCount;
        String plural = getPluralForm(count);
        String formatted = String.format(
                "%d. %s (%d %s, %.2f%%)",
                position, projection.getServiceName(), count, plural, percentage
        );

        return TopSubscriptionDto.builder()
                .position(position)
                .serviceName(projection.getServiceName())
                .count(count)
                .percentage(percentage)
                .formatted(formatted)
                .build();
    }

    private static String getPluralForm(long count) {
        if (count % 10 == 1 && count % 100 != 11) return "подписка";
        if (count % 10 >= 2 && count % 10 <= 4 && (count % 100 < 10 || count % 100 >= 20)) return "подписки";
        return "подписок";
    }
}
