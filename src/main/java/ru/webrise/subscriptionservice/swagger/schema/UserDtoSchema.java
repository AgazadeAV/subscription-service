package ru.webrise.subscriptionservice.swagger.schema;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import ru.webrise.subscriptionservice.dto.SubscriptionDto;

import java.util.List;
import java.util.UUID;

@Getter
public class UserDtoSchema {

    @Schema(description = "Идентификатор пользователя", example = "fc1a35f7-8a2f-4d9f-9cbb-8d4970a5db11")
    private UUID id;

    @Schema(description = "Полное имя пользователя", example = "John Doe")
    private String fullName;

    @Schema(description = "Электронная почта пользователя", example = "john.doe@example.com")
    private String email;

    @Schema(description = "Список подписок пользователя")
    private List<SubscriptionDto> subscriptions;
}
