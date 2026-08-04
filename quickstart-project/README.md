# API Dev Quickstart

A minimal Spring Boot REST API used to verify your installation for the **Fundamentals of API Development** course. It doesn't teach any course content by itself; it's just a fast way to confirm that Java, Maven, IntelliJ IDEA (or VS Code), and Postman are all working together before Day 1.

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
```

You should see:

```json
{"status":"UP","service":"api-dev-quickstart"}
{"message":"Hello, YourName! Your API dev setup is working."}
```

Swagger UI (interactive API docs) is available at <http://localhost:8080/swagger-ui.html>.

## Verify with Postman

1. Open Postman.
2. **Import** the collection at [`postman/api-dev-quickstart.postman_collection.json`](./postman/api-dev-quickstart.postman_collection.json).
3. With the app running, click **Run** on the collection (or send each request individually).
4. All three requests, `Health Check`, `Greeting`, and `OpenAPI Docs`, should return status `200` and pass their built-in tests.

## Run the JUnit5 Tests

From the command line:

```bash
mvn test
```

Or in IntelliJ or VS Code: right-click the `src/test/java` folder and choose **Run 'All Tests'**.

Both test classes (`HealthControllerTest`, `GreetingControllerTest`) should pass.

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
    │   │   └── controller/
    │   │       ├── HealthController.java
    │   │       └── GreetingController.java
    │   └── resources/application.properties
    └── test/
        └── java/com/apidev/quickstart/controller/
            ├── HealthControllerTest.java
            └── GreetingControllerTest.java
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
