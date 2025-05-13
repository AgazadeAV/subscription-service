package ru.webrise.subscriptionservice.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import ru.webrise.subscriptionservice.swagger.schema.CreateSubscriptionRequestSchema;

@Getter
@Setter
@Schema(implementation = CreateSubscriptionRequestSchema.class)
public class CreateSubscriptionRequest {

    @NotBlank(message = "Название сервиса обязательно для заполнения")
    private String serviceName;
}
