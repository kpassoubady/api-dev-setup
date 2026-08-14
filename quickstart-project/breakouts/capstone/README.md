# Day 4 Capstone: Evolve and Protect a Card Applications API

This is the only Day 4 breakout. In 60 minutes, learners evolve a credit union card decision API through three visible contracts, retire `v1` safely, protect the API with Spring Security, add a per-caller rate limit, verify the generated OpenAPI document, and make the supplied JUnit5 suite pass.

The implementation uses synthetic data and simplified classroom rules. It is not a lending model and must not be used for real credit decisions.

## Learning objectives

By the end of the capstone, learners will have applied the course objectives in one flow:

1. Classify the API audience and identify sensitive request fields.
2. Use resource-oriented paths, HTTP status codes, JSON contracts, and validation.
3. Compare `v1`, `v2`, and `v3` and identify which changes are actually breaking.
4. Keep `v1` working while publishing `Deprecation`, `Sunset`, and successor-version headers.
5. Document operations and security in the generated OpenAPI contract.
6. Distinguish authentication (`401`) from authorization (`403`) with Spring Security.
7. Enforce authorization before a per-caller rate limit (`429`).
8. Run deterministic MockMvc tests through Maven as a CI-ready quality gate.

## Project layout

| Path | Purpose |
| --- | --- |
| `start/` | Compiling Spring Boot project with seven numbered TODOs and executable acceptance tests |
| `solution/` | Complete reference implementation with the same structure |
| `01-design-the-evolution.md` | Audience, resource, contract, and lifecycle decisions |
| `02-build-the-versions.md` | `v1`, `v2`, `v3`, validation, OpenAPI, and retirement headers |
| `03-secure-and-limit.md` | Spring Security authentication, role authorization, and rate limiting |
| `04-test-and-deliver.md` | MockMvc, Maven, Swagger UI, and completion evidence |

## 60-minute run of show

| Time | Activity | Completion level |
| --- | --- | --- |
| 0-5 min | Open `start/`, compile, and run the failing acceptance suite | Setup |
| 5-12 min | Decide audience, sensitive fields, and breaking changes | Basic |
| 12-30 min | Complete decision logic and the three version contracts | Basic |
| 30-42 min | Complete Spring Security role rules | Intermediate |
| 42-50 min | Complete the three-request-per-minute limiter | Intermediate |
| 50-57 min | Run tests and inspect `/api-docs` or Swagger UI | Intermediate |
| 57-60 min | Record one lifecycle decision and one generated-code correction | Share |

Use the copy-paste prompt in each step. The coding assistant may propose code, but the learner owns the acceptance criteria and must review every change before running it.

## Start here

```bash
cd api-dev-setup/quickstart-project/breakouts/capstone/start
mvn -q -DskipTests compile
mvn test
```

The starter compiles. Its acceptance tests fail because the seven TODOs are incomplete. Do not weaken or delete tests to make them green.

## Contract at a glance

| Version | Caller | Contract purpose | Lifecycle state |
| --- | --- | --- | --- |
| `POST /api/v1/card-applications/decisions` | `MEMBER` or `UNDERWRITER` | Legacy `eligible` response | Deprecated but available |
| `POST /api/v2/card-applications/decisions` | `MEMBER` or `UNDERWRITER` | Traceable decision with ID and reasons | Supported member contract |
| `POST /api/v3/card-applications/decisions` | `UNDERWRITER` | Detailed ratio and risk-tier response | Internal underwriting contract |

Tests supply the training passwords `member-pass` and `underwriter-pass`; production code reads them from environment variables rather than storing credentials in source. HTTP Basic and in-memory users are local lab substitutes for the OAuth2/OIDC and short-lived tokens a financial API should use in production.

### Swagger UI Authentication

When testing the application via Swagger UI or command-line tools like `curl`, use the following Basic Authentication credentials:

- **Member Access** (can access `/api/v1/**` and `/api/v2/**`)
  - **Username:** `member`
  - **Password:** The value of your `CAPSTONE_MEMBER_PASSWORD` environment variable (tests use `member-pass`)
- **Underwriter Access** (can access `/api/v1/**`, `/api/v2/**`, and `/api/v3/**`)
  - **Username:** `underwriter`
  - **Password:** The value of your `CAPSTONE_UNDERWRITER_PASSWORD` environment variable (tests use `underwriter-pass`)

## Basic, intermediate, and stretch goals

### Basic: lifecycle and contracts

- Complete TODOs 1-5.
- Keep all three paths available.
- Make the first three contract tests pass.
- Explain why `v1` to `v2` is breaking and whether `v2` to `v3` truly required a new version.

### Intermediate: security and automated quality gates

- Complete TODOs 6-7.
- Produce `401`, `403`, and `429` on the correct paths.
- Make all seven acceptance tests pass with `mvn test`.
- Confirm the operations appear in Swagger UI or `/api-docs`.

### Stretch: deepen only after all tests pass

Choose one:

1. Add MockMvc cases for a declined application and a manual-review application.
2. Add OpenAPI `@ApiResponse` entries for `400`, `401`, `403`, and `429`.
3. Add the three requests and assertions to a new Postman collection beside the lab.
4. Draft the soft-deprecation, hard-deprecation, and sunset communication for `v1`.

## Definition of done

- [ ] The audience and sensitive fields are recorded.
- [ ] The `v1` retirement headers point consumers to a supported version.
- [ ] A member receives `200` on `v2`, `401` without credentials, and `403` on `v3`.
- [ ] An underwriter receives the richer `v3` response.
- [ ] The fourth authorized request in one minute receives `429` and `Retry-After`.
- [ ] `mvn test` passes all seven acceptance tests.
- [ ] The generated OpenAPI document exposes the three operations and Basic authentication scheme.
- [ ] The learner can name one correction made to LLM-generated code.

## Important production boundary

The limiter is single-instance and in-memory. A production credit union service needs a gateway or shared store, coordinated limits, stronger identity, object-level authorization, audit controls, privacy review, and validated underwriting policy. Those implementation details are intentionally outside this course catalog.
