package ru.webrise.subscriptionservice.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import ru.webrise.subscriptionservice.swagger.schema.CreateUserRequestSchema;

@Getter
@Setter
@Schema(implementation = CreateUserRequestSchema.class)
public class CreateUserRequest {

    @NotBlank(message = "Полное имя обязательно для заполнения")
    private String fullName;

    @Email(message = "Некорректный формат email")
    @NotBlank(message = "Email обязателен для заполнения")
    private String email;
}
