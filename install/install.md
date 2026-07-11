# Installation Instructions

This document provides the installation instructions for the **Fundamentals of API Development** course.

Click on the link for your operating system to view the detailed setup guide:

| Platform | Installation Guide |
|----------|---------------------|
| **macOS** | [macOS Install Guide](./install-mac.md) |
| **Windows** | [Windows Install Guide](./install-win.md) |

## What You Will Install

| Category | Tools / Packages |
|----------|-------------------|
| **JDK** | OpenJDK 17+ (recommended: JDK 21) |
| **Build Tool** | Apache Maven 3.9+ |
| **IDE** | IntelliJ IDEA (Community or Ultimate) |
| **API Client** | Postman |
| **Version Control** | Git |

> The course labs are built against a Spring Boot REST API, tested with JUnit5 and Postman, using IntelliJ as the IDE. See [`../catalog/api-dev-am-pm-outline.md`](../catalog/api-dev-am-pm-outline.md) for the full course outline.

## Verify Your Installation

After completing the OS-specific install guide, use the [`quickstart-project`](../quickstart-project/) to confirm every tool works end to end:

1. Open [`../quickstart-project`](../quickstart-project/) in IntelliJ IDEA as a Maven project.
2. Build and run it with Maven (`mvn spring-boot:run`).
3. Call its REST endpoints from Postman using the provided collection.
4. Run the JUnit5 tests (`mvn test`) from IntelliJ or the command line.

Full steps are in [`../quickstart-project/README.md`](../quickstart-project/README.md).

> If port 8080 is already in use (e.g. a previous `mvn spring-boot:run` was left running), see [Free Up Port 8080](../quickstart-project/README.md#free-up-port-8080) for macOS/Linux and Windows commands to kill it.

## Quick Verification Checklist

- [ ] `java -version` shows 17+ (21 recommended)
- [ ] `mvn -version` works and reports the same Java version
- [ ] `git --version` works
- [ ] IntelliJ IDEA opens and recognizes `quickstart-project` as a Maven project
- [ ] Postman is installed and can send a request
- [ ] `mvn spring-boot:run` starts `quickstart-project` on <http://localhost:8080>
- [ ] `GET http://localhost:8080/api/v1/health` returns `{"status":"UP", ...}` (via browser, curl, or Postman)
- [ ] The Postman collection's requests all pass their tests
- [ ] `mvn test` passes all JUnit5 tests
