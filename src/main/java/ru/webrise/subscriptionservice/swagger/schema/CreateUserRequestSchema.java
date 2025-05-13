package ru.webrise.subscriptionservice.swagger.schema;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

@Getter
public class CreateUserRequestSchema {

    @Schema(description = "Полное имя пользователя", example = "Иван Иванов")
    private String fullName;

    @Schema(description = "Электронная почта пользователя", example = "ivan.ivanov@example.com")
    private String email;
}
