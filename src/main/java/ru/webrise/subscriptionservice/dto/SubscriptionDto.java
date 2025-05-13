package ru.webrise.subscriptionservice.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import ru.webrise.subscriptionservice.swagger.schema.SubscriptionDtoSchema;

import java.util.UUID;

@Getter
@Setter
@Builder
@Schema(implementation = SubscriptionDtoSchema.class)
public class SubscriptionDto {

    private UUID id;
    private String serviceName;
}
