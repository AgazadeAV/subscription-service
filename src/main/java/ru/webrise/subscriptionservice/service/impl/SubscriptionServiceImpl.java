package ru.webrise.subscriptionservice.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.webrise.subscriptionservice.dto.CreateSubscriptionRequest;
import ru.webrise.subscriptionservice.dto.SubscriptionDto;
import ru.webrise.subscriptionservice.dto.TopSubscriptionDto;
import ru.webrise.subscriptionservice.dto.projection.TopServiceProjection;
import ru.webrise.subscriptionservice.exception.NotFoundException;
import ru.webrise.subscriptionservice.exception.SubscriptionAlreadyExistsException;
import ru.webrise.subscriptionservice.mapper.SubscriptionMapper;
import ru.webrise.subscriptionservice.model.Subscription;
import ru.webrise.subscriptionservice.model.User;
import ru.webrise.subscriptionservice.repository.SubscriptionRepository;
import ru.webrise.subscriptionservice.repository.UserRepository;
import ru.webrise.subscriptionservice.service.SubscriptionService;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class SubscriptionServiceImpl implements SubscriptionService {

    private final SubscriptionRepository subscriptionRepository;
    private final UserRepository userRepository;
    private final SubscriptionMapper subscriptionMapper;

    @Override
    @Transactional
    public SubscriptionDto addSubscription(UUID userId, CreateSubscriptionRequest request) {
        log.debug("Добавление подписки '{}' для пользователя {}", request.getServiceName(), userId);

        User user = userRepository.findById(userId)
                .orElseThrow(() -> {
                    log.warn("Пользователь не найден при добавлении подписки: {}", userId);
                    return new NotFoundException("Пользователь не найден: " + userId);
                });

        if (subscriptionRepository.existsByUserIdAndServiceName(userId, request.getServiceName())) {
            log.warn("Подписка '{}' уже существует у пользователя {}", request.getServiceName(), userId);
            throw new SubscriptionAlreadyExistsException("Подписка уже существует: " + request.getServiceName());
        }

        Subscription subscription = subscriptionMapper.mapToSubscription(request);
        subscription.setUser(user);

        Subscription saved = subscriptionRepository.save(subscription);
        log.info("Подписка '{}' добавлена для пользователя {}", saved.getServiceName(), userId);
        return subscriptionMapper.mapToSubscriptionDto(saved);
    }

    @Override
    public List<SubscriptionDto> getUserSubscriptions(UUID userId) {
        log.debug("Получение подписок для пользователя {}", userId);
        if (!userRepository.existsById(userId)) {
            log.warn("Пользователь не найден при получении подписок: {}", userId);
            throw new NotFoundException("Пользователь не найден: " + userId);
        }

        return subscriptionRepository.findAllByUserId(userId).stream()
                .map(subscriptionMapper::mapToSubscriptionDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void deleteSubscription(UUID userId, UUID subscriptionId) {
        log.debug("Попытка удалить подписку {} для пользователя {}", subscriptionId, userId);
        Subscription subscription = subscriptionRepository.findById(subscriptionId)
                .orElseThrow(() -> {
                    log.warn("Подписка не найдена: {}", subscriptionId);
                    return new NotFoundException("Подписка не найдена: " + subscriptionId);
                });

        if (!subscription.getUser().getId().equals(userId)) {
            log.error("Подписка {} не принадлежит пользователю {}", subscriptionId, userId);
            throw new IllegalArgumentException("Эта подписка не принадлежит пользователю");
        }

        subscriptionRepository.deleteById(subscriptionId);
        log.info("Подписка {} удалена для пользователя {}", subscriptionId, userId);
    }

    @Override
    public List<TopSubscriptionDto> getTopSubscriptions() {
        log.debug("Получение топ-3 самых популярных подписок");

        long totalCount = subscriptionRepository.count();
        if (totalCount == 0) return List.of();

        List<TopServiceProjection> topServices = subscriptionRepository.findTopThreeServices();

        AtomicInteger position = new AtomicInteger(1);
        return topServices.stream()
                .map(projection -> subscriptionMapper.mapToTopSubscriptionDto(projection, position.getAndIncrement(), totalCount))
                .toList();
    }
}
