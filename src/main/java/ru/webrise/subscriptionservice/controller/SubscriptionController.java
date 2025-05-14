package ru.webrise.subscriptionservice.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.webrise.subscriptionservice.dto.CreateSubscriptionRequest;
import ru.webrise.subscriptionservice.dto.SubscriptionDto;
import ru.webrise.subscriptionservice.dto.TopSubscriptionDto;
import ru.webrise.subscriptionservice.service.SubscriptionService;
import ru.webrise.subscriptionservice.swagger.SubscriptionApiSpec;

import java.util.List;
import java.util.UUID;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping
public class SubscriptionController implements SubscriptionApiSpec {

    private final SubscriptionService subscriptionService;

    @PostMapping("/users/{userId}/subscriptions")
    public ResponseEntity<SubscriptionDto> addSubscription(
            @PathVariable(name = "userId") UUID userId,
            @Valid @RequestBody CreateSubscriptionRequest request) {
        log.info("Получен запрос на добавление подписки '{}' для пользователя {}", request.getServiceName(), userId);
        return ResponseEntity.ok(subscriptionService.addSubscription(userId, request));
    }

    @GetMapping("/users/{userId}/subscriptions")
    public ResponseEntity<List<SubscriptionDto>> getSubscriptions(
            @PathVariable(name = "userId") UUID userId) {
        log.info("Получен запрос на получение списка подписок пользователя {}", userId);
        return ResponseEntity.ok(subscriptionService.getUserSubscriptions(userId));
    }

    @DeleteMapping("/users/{userId}/subscriptions/{subscriptionId}")
    public ResponseEntity<Void> deleteSubscription(
            @PathVariable(name = "userId") UUID userId,
            @PathVariable(name = "subscriptionId") UUID subscriptionId) {
        log.info("Получен запрос на удаление подписки {} пользователя {}", subscriptionId, userId);
        subscriptionService.deleteSubscription(userId, subscriptionId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/subscriptions/top")
    public ResponseEntity<List<TopSubscriptionDto>> getTopSubscriptions() {
        log.info("Получен запрос на получение топовых подписок");
        return ResponseEntity.ok(subscriptionService.getTopSubscriptions());
    }
}
