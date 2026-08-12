# Quickstart Project

This directory contains the minimal Spring Boot REST API used to verify the toolchain for the **Fundamentals of API Development** course. It is a sanity-check project only and should not be expanded into course exercises.

## Tech Stack

- Java 21
- Spring Boot 3.2.5
- Maven
- springdoc-openapi 2.5.0
- JUnit 5 + Spring Boot Test (MockMvc)

## Directory Layout

```text
quickstart-project/
├── pom.xml
├── README.md
├── postman/
│   └── api-dev-quickstart.postman_collection.json
├── src/main/java/com/apidev/quickstart/
│   ├── QuickstartApplication.java
│   ├── controller/
│   │   ├── HealthController.java
│   │   ├── GreetingController.java
│   │   ├── CurrencyController.java
│   │   └── TemperatureController.java
│   └── service/
│       └── CurrencyConversionService.java
├── src/main/resources/
│   └── application.properties
└── src/test/java/com/apidev/quickstart/
    ├── controller/
    │   ├── HealthControllerTest.java
    │   ├── GreetingControllerTest.java
    │   ├── CurrencyControllerTest.java
    │   └── TemperatureControllerTest.java
    └── service/
        └── CurrencyConversionServiceTest.java
```

## Endpoints

| Method | Path | Description |
| --- | --- | --- |
| GET | `/api/v1/health` | Health check: `{"status":"UP","service":"api-dev-quickstart"}` |
| GET | `/api/v1/greetings/{name}` | Personalized greeting |
| GET | `/api/v1/currency/usd-to-inr?amount={amount}` | USD-to-INR conversion demo |
| GET | `/api/v1/temperature/fahrenheit-to-celsius?fahrenheit={value}` | Fahrenheit-to-Celsius conversion demo |
| GET | `/api-docs` / `/api-docs.yaml` | Raw OpenAPI spec |
| GET | `/swagger-ui.html` | Swagger UI interactive docs |
| GET | `/redoc.html` | Redoc reference layout |

## Run the Application

```bash
cd quickstart-project
mvn clean compile
mvn spring-boot:run
```

Verify with curl:

```bash
curl http://localhost:8080/api/v1/health
curl http://localhost:8080/api/v1/greetings/Learner
curl "http://localhost:8080/api/v1/currency/usd-to-inr?amount=100"
curl "http://localhost:8080/api/v1/temperature/fahrenheit-to-celsius?fahrenheit=100"
```

## Run Tests

```bash
mvn test
```

All JUnit tests should pass. The Postman collection at `postman/api-dev-quickstart.postman_collection.json` can also be imported and run; all requests should return `200` and pass their built-in tests.

## Configuration

`src/main/resources/application.properties`:

```properties
server.port=8080
springdoc.api-docs.path=/api-docs
springdoc.swagger-ui.path=/swagger-ui.html
```

## Content Guidelines

- Keep this project a minimal installation sanity check only.
- Do not expand it into course exercises; those belong in `api-dev-companion`.
- Keep `README.md`, the Postman collection, and JUnit tests in sync when endpoints change.
- Use port 8080 by default. To use a different port, pass `--server.port=8081` or update `server.port` in `application.properties`.
