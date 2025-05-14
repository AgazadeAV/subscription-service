package ru.webrise.subscriptionservice.swagger;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import ru.webrise.subscriptionservice.dto.CreateSubscriptionRequest;
import ru.webrise.subscriptionservice.dto.SubscriptionDto;
import ru.webrise.subscriptionservice.dto.TopSubscriptionDto;

import java.util.List;
import java.util.UUID;

@Tag(name = "API Подписок", description = "Управление подписками. Версия 1.0.0")
public interface SubscriptionApiSpec {

    @Operation(summary = "Добавить подписку", description = "Добавляет новую подписку пользователю.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Подписка добавлена",
                    content = @Content(schema = @Schema(implementation = SubscriptionDto.class))),
            @ApiResponse(responseCode = "404", description = "Пользователь не найден",
                    content = @Content(schema = @Schema(hidden = true))),
            @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера",
                    content = @Content(schema = @Schema(hidden = true)))
    })
    ResponseEntity<SubscriptionDto> addSubscription(UUID userId, @RequestBody CreateSubscriptionRequest request);

    @Operation(summary = "Получить подписки пользователя", description = "Возвращает все подписки пользователя.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Подписки найдены",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = SubscriptionDto.class)))),
            @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера",
                    content = @Content(schema = @Schema(hidden = true)))
    })
    ResponseEntity<List<SubscriptionDto>> getSubscriptions(UUID userId);

    @Operation(summary = "Удалить подписку", description = "Удаляет подписку у пользователя.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Подписка удалена",
                    content = @Content(schema = @Schema(hidden = true))),
            @ApiResponse(responseCode = "404", description = "Подписка или пользователь не найдены",
                    content = @Content(schema = @Schema(hidden = true))),
            @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера",
                    content = @Content(schema = @Schema(hidden = true)))
    })
    ResponseEntity<Void> deleteSubscription(UUID userId, UUID subscriptionId);

    @Operation(summary = "Топ 3 подписок", description = "Возвращает топ 3 самых популярных подписок.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Топ подписок получен",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = TopSubscriptionDto.class)))),
            @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера",
                    content = @Content(schema = @Schema(hidden = true)))
    })
    ResponseEntity<List<TopSubscriptionDto>> getTopSubscriptions();
}
