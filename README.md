# 📦 Subscription Service

Микросервис для управления пользователями и их подписками на цифровые сервисы. Реализован с использованием Spring Boot 3, PostgreSQL, Docker и Swagger (OpenAPI 3).

---

## 🚀 Возможности

* Создание, получение, обновление и удаление пользователей
* Добавление, просмотр и удаление подписок пользователя
* Получение топ-3 самых популярных подписок
* Swagger-документация для всех REST-эндпоинтов
* Обработка ошибок с понятными сообщениями
* Поддержка Docker Compose для локального запуска

---

## 🧱 Стек технологий

* **Java 17**
* **Spring Boot 3**
* **Spring Data JPA**
* **PostgreSQL**
* **Lombok**
* **MapStruct**
* **OpenAPI (Springdoc Swagger)**
* **Docker / Docker Compose**
* **SLF4J + Logback**

---

## 📂 Структура проекта

```
subscription-service/
│
├── src/
│   ├── main/
│   │   ├── java/ru/webrise/subscriptionservice/
│   │   │   ├── controller/        # REST-контроллеры
│   │   │   ├── service/           # Интерфейсы сервисов
│   │   │   ├── service/impl/      # Реализация бизнес-логики
│   │   │   ├── dto/               # DTO-классы
│   │   │   ├── model/             # JPA-сущности
│   │   │   ├── repository/        # JPA-репозитории
│   │   │   ├── mapper/            # MapStruct-мапперы
│   │   │   ├── exception/         # Обработка ошибок
│   │   │   ├── swagger/           # Swagger-интерфейсы и схемы
│   │   │   └── config/            # Конфигурации
│   │   └── resources/
│   │       └── application.yml    # Конфигурация Spring Boot
│
├── Dockerfile
├── docker-compose.yml
├── pom.xml
└── README.md
```

---

## 📦 Запуск проекта

### ✅ Предварительные требования

* Установлен **Docker** и **Docker Compose**

## 🐳 Запуск с Docker Compose

---

## 🛠 Управление контейнерами

### 🚀 Запуск с пересборкой (если изменился код или Dockerfile)

```bash
docker compose up --build
```

### 🚀 Запуск без пересборки (если код не менялся)

```bash
docker compose up -d
```

### 🛑 Остановка контейнеров

```bash
docker compose down
```

### 🔄 Перезапуск контейнеров (без пересборки)

```bash
docker compose down
docker compose up -d
```

### 🔄 Перезапуск контейнеров с пересборкой образов

```bash
docker compose down
docker compose up --build
```

---

## 🌐 Swagger UI

После запуска доступен по адресу:
📎 [http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)

---

## 📘 API Эндпоинты

### Пользователи

| Метод    | URL           | Описание                    |
| -------- | ------------- | --------------------------- |
| `POST`   | `/users`      | Создать пользователя        |
| `GET`    | `/users/{id}` | Получить пользователя по ID |
| `PUT`    | `/users/{id}` | Обновить пользователя       |
| `DELETE` | `/users/{id}` | Удалить пользователя        |

### Подписки

| Метод    | URL                                              | Описание                        |
| -------- | ------------------------------------------------ | ------------------------------- |
| `POST`   | `/users/{userId}/subscriptions`                  | Добавить подписку пользователю  |
| `GET`    | `/users/{userId}/subscriptions`                  | Получить подписки пользователя  |
| `DELETE` | `/users/{userId}/subscriptions/{subscriptionId}` | Удалить подписку                |
| `GET`    | `/subscriptions/top`                             | Топ-3 самых популярных подписки |

---

## ⚠️ Обработка ошибок

* `404 Not Found` — пользователь или подписка не найдены
* `400 Bad Request` — ошибка валидации
* `500 Internal Server Error` — непредвиденная ошибка

Ошибки возвращаются в формате:

```json
{
  "error": "User not found: {id}"
}
```

---

## 🗄️ Миграции (Liquibase)

Миграции базы данных выполняются через **Liquibase**. Главный changelog:

```
src/main/resources/db/changelog/db.changelog-master.yaml
```

Spring Boot применяет миграции автоматически при старте приложения:

```yaml
spring:
liquibase:
change-log: classpath:db/changelog/db.changelog-master.yaml
```

Для ручного запуска:

```bash
mvn liquibase:update
```

---

## 👤 Автор

Разработано с ❤️ разработчиком **Агазаде Азером** — Java Backend Developer

🐱‍ Github: [@AgazadeAV](https://github.com/AgazadeAV)

📫 Telegram: [@azer\_aghazadeh](https://t.me/azer_aghazadeh)