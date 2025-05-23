package ru.webrise.subscriptionservice.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI subscriptionServiceOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("API сервиса подписок")
                        .description("API для управления пользователями и подписками")
                        .version("1.0.0")
                        .contact(new Contact()
                                .name("Команда WebRise")
                                .email("support@webrise.ru")));
    }
}
