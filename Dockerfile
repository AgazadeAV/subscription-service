# Этап 1: Сборка с кешированием зависимостей
FROM maven:3.9.6-eclipse-temurin-17-alpine AS builder

WORKDIR /app

# Сначала копируем только pom.xml и скачиваем зависимости (будет кешироваться)
COPY pom.xml .
RUN mvn dependency:go-offline

# Потом копируем src (если код не менялся - зависимости не будут заново качаться)
COPY src ./src

# Сборка проекта
RUN mvn clean package spring-boot:repackage -DskipTests

# Этап 2: Лёгкий runtime-образ
FROM eclipse-temurin:17-jdk-alpine

WORKDIR /app

COPY --from=builder /app/target/subscription-service-0.0.1-SNAPSHOT.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]
