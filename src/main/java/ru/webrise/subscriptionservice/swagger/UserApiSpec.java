package ru.webrise.subscriptionservice.swagger;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import ru.webrise.subscriptionservice.dto.CreateUserRequest;
import ru.webrise.subscriptionservice.dto.UserDto;

import java.util.UUID;

@Tag(name = "API Пользователей", description = "Управление пользователями. Версия 1.0.0")
public interface UserApiSpec {

    @Operation(summary = "Создать пользователя", description = "Создаёт нового пользователя и возвращает его.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Пользователь создан",
                    content = @Content(schema = @Schema(implementation = UserDto.class))),
            @ApiResponse(responseCode = "400", description = "Ошибка валидации",
                    content = @Content(schema = @Schema(hidden = true))),
            @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера",
                    content = @Content(schema = @Schema(hidden = true)))
    })
    ResponseEntity<UserDto> createUser(@RequestBody CreateUserRequest request);

    @Operation(summary = "Получить пользователя по ID", description = "Возвращает пользователя по ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Пользователь найден",
                    content = @Content(schema = @Schema(implementation = UserDto.class))),
            @ApiResponse(responseCode = "404", description = "Пользователь не найден",
                    content = @Content(schema = @Schema(hidden = true))),
            @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера",
                    content = @Content(schema = @Schema(hidden = true)))
    })
    ResponseEntity<UserDto> getUserById(UUID id);

    @Operation(summary = "Обновить пользователя", description = "Обновляет пользователя по ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Пользователь обновлён",
                    content = @Content(schema = @Schema(implementation = UserDto.class))),
            @ApiResponse(responseCode = "400", description = "Ошибка валидации",
                    content = @Content(schema = @Schema(hidden = true))),
            @ApiResponse(responseCode = "404", description = "Пользователь не найден",
                    content = @Content(schema = @Schema(hidden = true))),
            @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера",
                    content = @Content(schema = @Schema(hidden = true)))
    })
    ResponseEntity<UserDto> updateUser(UUID id, @RequestBody CreateUserRequest request);

    @Operation(summary = "Удалить пользователя", description = "Удаляет пользователя по ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Пользователь удалён",
                    content = @Content(schema = @Schema(hidden = true))),
            @ApiResponse(responseCode = "404", description = "Пользователь не найден",
                    content = @Content(schema = @Schema(hidden = true))),
            @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера",
                    content = @Content(schema = @Schema(hidden = true)))
    })
    ResponseEntity<Void> deleteUser(UUID id);
}
