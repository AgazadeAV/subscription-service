package ru.webrise.subscriptionservice.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import ru.webrise.subscriptionservice.swagger.schema.UserDtoSchema;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Builder
@Schema(implementation = UserDtoSchema.class)
public class UserDto {

    private UUID id;
    private String fullName;
    private String email;
    private List<SubscriptionDto> subscriptions;
}
