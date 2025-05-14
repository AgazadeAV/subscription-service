package ru.webrise.subscriptionservice.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import ru.webrise.subscriptionservice.swagger.schema.TopSubscriptionDtoSchema;

@Getter
@Setter
@Builder
@Schema(implementation = TopSubscriptionDtoSchema.class)
public class TopSubscriptionDto {
    private int position;
    private String serviceName;
    private long count;
    private double percentage;
    private String formatted;
}
