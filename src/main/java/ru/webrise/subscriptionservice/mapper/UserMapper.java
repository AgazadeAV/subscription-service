package ru.webrise.subscriptionservice.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.webrise.subscriptionservice.dto.CreateUserRequest;
import ru.webrise.subscriptionservice.dto.UserDto;
import ru.webrise.subscriptionservice.model.User;

@Mapper(componentModel = "spring", uses = {SubscriptionMapper.class})
public interface UserMapper {

    User toEntity(CreateUserRequest request);

    @Mapping(source = "subscriptions", target = "subscriptions")
    UserDto toDto(User user);
}
