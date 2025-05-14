package ru.webrise.subscriptionservice.service;

import ru.webrise.subscriptionservice.dto.CreateUserRequest;
import ru.webrise.subscriptionservice.dto.UserDto;

import java.util.UUID;

public interface UserService {
    UserDto createUser(CreateUserRequest request);

    UserDto getUserById(UUID id);

    UserDto updateUser(UUID id, CreateUserRequest request);

    void deleteUser(UUID id);
}
