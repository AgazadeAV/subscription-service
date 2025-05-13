package ru.webrise.subscriptionservice.service;

import ru.webrise.subscriptionservice.dto.CreateUserRequest;
import ru.webrise.subscriptionservice.dto.UserDto;

import java.util.UUID;

public interface UserService {
    UserDto create(CreateUserRequest request);

    UserDto getById(UUID id);

    UserDto update(UUID id, CreateUserRequest request);

    void delete(UUID id);
}
