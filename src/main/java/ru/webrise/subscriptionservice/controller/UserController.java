package ru.webrise.subscriptionservice.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.webrise.subscriptionservice.dto.CreateUserRequest;
import ru.webrise.subscriptionservice.dto.UserDto;
import ru.webrise.subscriptionservice.service.UserService;
import ru.webrise.subscriptionservice.swagger.UserApiSpec;

import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController implements UserApiSpec {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<UserDto> create(@Valid @RequestBody CreateUserRequest request) {
        log.info("Получен запрос на создание пользователя: {}", request.getEmail());
        return ResponseEntity.ok(userService.create(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getById(@PathVariable UUID id) {
        log.info("Получен запрос на получение пользователя с ID: {}", id);
        return ResponseEntity.ok(userService.getById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDto> update(@PathVariable UUID id, @Valid @RequestBody CreateUserRequest request) {
        log.info("Получен запрос на обновление пользователя {} с данными: {}", id, request);
        return ResponseEntity.ok(userService.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        log.info("Получен запрос на удаление пользователя с ID: {}", id);
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
