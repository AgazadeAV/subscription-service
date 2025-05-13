package ru.webrise.subscriptionservice.swagger.schema;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

import java.util.UUID;

@Getter
public class SubscriptionDtoSchema {

    @Schema(description = "Идентификатор подписки", example = "b3276dc1-0ff5-4219-a5bc-4e67edebdc0f")
    private UUID id;

    @Schema(description = "Название подписанного сервиса", example = "Netflix")
    private String serviceName;
}
