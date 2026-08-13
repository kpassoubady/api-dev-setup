# API Dev Quickstart

A minimal Spring Boot REST API used to verify your installation for the **Fundamentals of API Development** course. The baseline application is a fast way to confirm that Java, Maven, IntelliJ IDEA (or VS Code), and Postman are working together before Day 1; optional lab assets remain separate under `breakouts/`.

## Prerequisites

Complete the [macOS](../install/install-mac.md) or [Windows](../install/install-win.md) install guide first.

## Open in IntelliJ IDEA

1. Launch IntelliJ IDEA.
2. **File > Open...** and select the `quickstart-project` folder.
3. IntelliJ should detect `pom.xml` and import it as a Maven project automatically (this can take a minute the first time while it downloads dependencies).

## Run from the Command Line

```bash
cd quickstart-project
mvn clean compile
mvn spring-boot:run
```

The app starts on port 8080. Leave it running and move to the "Verify with Postman" section below. Stop it anytime with `Ctrl+C`.

## Open in VS Code

1. Launch VS Code.
2. **File > Open Folder...** and select the `quickstart-project` folder.
3. VS Code will detect `pom.xml` and prompt you to import the Maven project. Accept it.

## Run from IntelliJ IDEA

Open `src/main/java/com/apidev/quickstart/QuickstartApplication.java` and click the green run arrow next to `main`.

## Verify with a Browser or curl

```bash
curl http://localhost:8080/api/v1/health
curl http://localhost:8080/api/v1/greetings/YourName
curl "http://localhost:8080/api/v1/currency/usd-to-inr?amount=100"
curl "http://localhost:8080/api/v1/temperature/fahrenheit-to-celsius?fahrenheit=212"
```

You should see something like:

```json
{"status":"UP","service":"api-dev-quickstart"}
{"message":"Hello, YourName! Your API dev setup is working."}
{"from":"USD","to":"INR","amount":100.0,"rate":83.12,"convertedAmount":8312.0}
{"fahrenheit":212.0,"celsius":100.0}
```

`rate` and `convertedAmount` vary slightly because the demo service adds a small random jitter.

## API Documentation (Swagger UI & OpenAPI)

This project includes `springdoc-openapi` 2.5.0, which automatically generates an OpenAPI 3.0 specification from your controllers at runtime. No separate generation step is needed — just start the app.

### View the Interactive Docs

| Renderer | URL | Style |
| :--- | :--- | :--- |
| **Swagger UI** | `http://localhost:8080/swagger-ui.html` | Interactive "try it out" console |
| **Redoc** | `http://localhost:8080/redoc.html` | Clean three-panel reference layout |

Both render the same OpenAPI spec served at `/api-docs`. The `redoc.html` page is included in `src/main/resources/static/`.

### Fetch the Raw Spec

```bash
# JSON format
curl http://localhost:8080/api-docs

# YAML format
curl http://localhost:8080/api-docs.yaml

# Save to a file
curl http://localhost:8080/api-docs -o openapi.json
```

### How It Works

`springdoc-openapi-starter-webmvc-ui` introspects your `@RestController` classes on startup and serves the generated spec at `/api-docs` (JSON) and `/swagger-ui.html` (interactive console). These paths are configured in `src/main/resources/application.properties`:

```properties
springdoc.api-docs.path=/api-docs
springdoc.swagger-ui.path=/swagger-ui.html
```

To enrich the generated spec with descriptions, examples, and error responses, add `@Operation`, `@ApiResponse`, and `@Parameter` annotations to your controller methods. See the course slides and `api-dev-companion/day2/concepts/springdoc-openapi.md` for the full annotation reference.

## Verify with Postman

1. Open Postman.
2. **Import** the collection at [`postman/api-dev-quickstart.postman_collection.json`](./postman/api-dev-quickstart.postman_collection.json).
3. With the app running, click **Run** on the collection (or send each request individually).
4. All five requests — `Health Check`, `Greeting`, `Currency Conversion`, `Temperature Conversion`, and `OpenAPI Docs` — should return status `200` and pass their built-in tests.

## Run the JUnit5 Tests

From the command line:

```bash
mvn test
```

Or in IntelliJ or VS Code: right-click the `src/test/java` folder and choose **Run 'All Tests'**.

All test classes (`HealthControllerTest`, `GreetingControllerTest`, `CurrencyControllerTest`, `TemperatureControllerTest`, and `CurrencyConversionServiceTest`) should pass.

## Optional Day 3 Breakout

After completing the baseline setup, teams may choose one challenge in the
[Build API Endpoints breakout](./breakouts/build-api-endpoints/README.md). The lab assets are separate from the
baseline source and include starter tests, AI prompts, and reference solutions.

## Project Structure

```text
quickstart-project/
├── pom.xml
├── postman/
│   └── api-dev-quickstart.postman_collection.json
└── src/
    ├── main/
    │   ├── java/com/apidev/quickstart/
    │   │   ├── QuickstartApplication.java
    │   │   ├── controller/
    │   │   │   ├── HealthController.java
    │   │   │   ├── GreetingController.java
    │   │   │   ├── CurrencyController.java
    │   │   │   └── TemperatureController.java
    │   │   └── service/
    │   │       └── CurrencyConversionService.java
    │   └── resources/application.properties
    └── test/
        └── java/com/apidev/quickstart/
            ├── controller/
            │   ├── HealthControllerTest.java
            │   ├── GreetingControllerTest.java
            │   ├── CurrencyControllerTest.java
            │   └── TemperatureControllerTest.java
            └── service/
                └── CurrencyConversionServiceTest.java
```

## Troubleshooting

- **Port 8080 already in use**: free up the port (see below), or run with a different port: `mvn spring-boot:run -Dspring-boot.run.arguments=--server.port=8081` (update the Postman `baseUrl` variable to match).
- **Maven can't resolve dependencies**: check your internet connection, or see the proxy/`settings.xml` notes in the [install guides](../install/install.md).
- **IntelliJ shows Maven import errors**: right-click `pom.xml` and choose **Maven > Reload project**.

### Free Up Port 8080

Useful any time port 8080 is already taken, not just during this quickstart, for example if a previous `mvn spring-boot:run` was left running or another app grabbed the port.

**macOS / Linux:**

```bash
lsof -ti:8080 | xargs kill -9
```

**Windows (Command Prompt):**

```cmd
netstat -ano | findstr :8080
taskkill /PID <PID> /F
```

(Replace `<PID>` with the process ID from the last column of the `netstat` output.)

**Windows (PowerShell):**

```powershell
Get-Process -Id (Get-NetTCPConnection -LocalPort 8080).OwningProcess | Stop-Process -Force
```
