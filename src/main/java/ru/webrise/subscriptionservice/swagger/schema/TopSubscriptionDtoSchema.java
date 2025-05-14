package ru.webrise.subscriptionservice.swagger.schema;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

@Getter
public class TopSubscriptionDtoSchema {

    @Schema(description = "Порядковый номер в списке топа", example = "1")
    private int position;

    @Schema(description = "Название сервиса", example = "Netflix")
    private String serviceName;

    @Schema(description = "Количество подписок на сервис", example = "42")
    private long count;

    @Schema(description = "Процент от общего количества подписок", example = "21.00")
    private double percentage;

    @Schema(description = "Отформатированная строка для отображения", example = "1. Netflix (42 подписки, 21.00%)")
    private String formatted;
}
