package ru.webrise.subscriptionservice.swagger.schema;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

@Getter
public class CreateSubscriptionRequestSchema {

    @Schema(description = "Название сервиса подписки", example = "Netflix")
    private String serviceName;
}
