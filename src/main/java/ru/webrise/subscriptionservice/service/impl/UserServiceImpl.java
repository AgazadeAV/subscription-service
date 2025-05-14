package ru.webrise.subscriptionservice.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.webrise.subscriptionservice.dto.CreateUserRequest;
import ru.webrise.subscriptionservice.dto.UserDto;
import ru.webrise.subscriptionservice.exception.NotFoundException;
import ru.webrise.subscriptionservice.mapper.UserMapper;
import ru.webrise.subscriptionservice.model.User;
import ru.webrise.subscriptionservice.repository.UserRepository;
import ru.webrise.subscriptionservice.service.UserService;

import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    @Transactional
    public UserDto createUser(CreateUserRequest request) {
        log.debug("Создание пользователя с email: {}", request.getEmail());
        User user = userMapper.toEntity(request);
        User saved = userRepository.save(user);
        log.info("Пользователь создан с ID: {}", saved.getId());
        return userMapper.toDto(saved);
    }

    @Override
    public UserDto getUserById(UUID id) {
        log.debug("Получение пользователя с ID: {}", id);
        User user = userRepository.findById(id)
                .orElseThrow(() -> {
                    log.warn("Пользователь не найден: {}", id);
                    return new NotFoundException("Пользователь не найден: " + id);
                });
        return userMapper.toDto(user);
    }

    @Override
    @Transactional
    public UserDto updateUser(UUID id, CreateUserRequest request) {
        log.debug("Обновление пользователя с ID: {}", id);
        User user = userRepository.findById(id)
                .orElseThrow(() -> {
                    log.warn("Пользователь не найден для обновления: {}", id);
                    return new NotFoundException("Пользователь не найден: " + id);
                });

        user.setFullName(request.getFullName());
        user.setEmail(request.getEmail());
        User updated = userRepository.save(user);
        log.info("Пользователь обновлён: {}", updated.getId());
        return userMapper.toDto(updated);
    }

    @Override
    @Transactional
    public void deleteUser(UUID id) {
        log.debug("Удаление пользователя с ID: {}", id);
        if (!userRepository.existsById(id)) {
            log.warn("Пользователь не найден для удаления: {}", id);
            throw new NotFoundException("Пользователь не найден: " + id);
        }
        userRepository.deleteById(id);
        log.info("Пользователь удалён: {}", id);
    }
}
