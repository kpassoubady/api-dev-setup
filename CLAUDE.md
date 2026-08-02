# Project Overview

This repository is the **setup and environment verification** companion for the **Fundamentals of API Development** course. It contains the installation guides, a pre-class welcome message, the course outline, and a small Spring Boot quickstart project that students use to verify their toolchain before Day 1.

## Directory Layout

- `catalog/`: Course delivery outline and session schedules.
- `install/`: Installation instructions for the course toolchain.
  - `install.md`: Platform-agnostic overview with links to OS-specific guides.
  - `install-mac.md`: Step-by-step setup for macOS.
  - `install-win.md`: Step-by-step setup for Windows.
- `quickstart-project/`: A minimal Spring Boot REST API used to verify the installation end to end.
- `Welcome.md`: Student-facing pre-class message, checklist, and quick links.
- `llm-context/`: Stores context for LLM agents.

## Related Repositories

- [api-dev](https://github.com/kpassoubady/api-dev): Main course catalog, outlines, and session materials.
- [api-dev-companion](https://github.com/kpassoubady/api-dev-companion): Starter code and exercise handouts for students (`start/`) and reference solutions (`solution/`).

## Verification Steps

1. Install the toolchain by following [`install/install.md`](install/install.md) (macOS or Windows).
2. Open [`quickstart-project`](quickstart-project/) in IntelliJ IDEA as a Maven project.
3. Run the application:
   ```bash
   cd quickstart-project
   mvn spring-boot:run
   ```
4. Verify the running service:
   ```bash
   curl http://localhost:8080/api/v1/health
   ```
   Expected response includes `{"status":"UP", ...}`.
5. Import and run the Postman collection at `quickstart-project/postman/api-dev-quickstart.postman_collection.json`; all requests should return `200` and pass their tests.
6. Run the JUnit5 tests:
   ```bash
   mvn test
   ```
   All tests in `HealthControllerTest` and `GreetingControllerTest` should pass.

## Content Guidelines

- Keep installation instructions tightly scoped to environment setup; course concepts belong in `api-dev` or `api-dev-companion`.
- OS-specific install guides should live in `install/install-mac.md` and `install/install-win.md`; common prerequisites and verification steps belong in `install/install.md`.
- The `quickstart-project` should remain a minimal sanity-check project only; do not expand it into course exercises.
- When adding screenshots or exported PDFs, keep file sizes reasonable and place images next to the document that references them.
- Maintain the `Welcome.md` pre-class checklist in sync with `install/install.md` and `quickstart-project/README.md`.
