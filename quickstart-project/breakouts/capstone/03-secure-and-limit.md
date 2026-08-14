# Step 3: Secure and limit the API

**Time budget:** 20 minutes

Spring Security, test support, users, password encoding, HTTP Basic plumbing, and filter ordering are preconfigured. Complete only TODOs 6-7.

## Access matrix

| Path | MEMBER | UNDERWRITER | No credentials |
| --- | --- | --- | --- |
| `/api/v1/**` | Allowed | Allowed | `401` |
| `/api/v2/**` | Allowed | Allowed | `401` |
| `/api/v3/**` | `403` | Allowed | `401` |
| OpenAPI and Swagger UI | Public for this lab | Public for this lab | Allowed |

The filter is deliberately placed after Spring Security's `AuthorizationFilter`: authentication first, authorization second, rate limiting third.

## TODO 6: authorization rules

In `SecurityConfig`, replace `anyRequest().permitAll()` with ordered request matchers that implement the matrix. Keep CSRF disabled for this stateless classroom API and keep HTTP Basic enabled.

### Copy-paste LLM prompt

```text
Read start/src/main/java/com/apidev/quickstart/security/SecurityConfig.java and the security tests in CardApplicationControllerTest.java. Implement only TODO 6. Configure ordered Spring Security request matchers so v3 requires UNDERWRITER, v1 and v2 accept MEMBER or UNDERWRITER, Swagger UI, `/api-docs`, and `/api-docs.yaml` remain public for the lab, and every other request requires authentication. Preserve the preconfigured users, password encoder, HTTP Basic, CSRF setting, and RateLimitFilter ordering. Do not use permitAll for /api/** and do not change tests. Explain why missing credentials produce 401 while a member calling v3 produces 403, then run the two focused tests.
```

```bash
mvn -Dtest=CardApplicationControllerTest#missingCredentialsReturn401 test
mvn -Dtest=CardApplicationControllerTest#memberCannotCallUnderwriterOnlyV3 test
```

## TODO 7: three requests per minute

Use a thread-safe in-memory map keyed by authenticated username and request URI. The first three authorized requests in a 60-second fixed window pass. The fourth returns:

- HTTP `429`
- `Retry-After: 60`
- `RateLimit-Limit: 3`
- JSON error field `rate_limit_exceeded`

Keep `reset()` functional so tests remain independent. Skip anonymous users and non-API paths. Do not move the filter before authorization.

### Copy-paste LLM prompt

```text
Read RateLimitFilter.java, SecurityConfig.java, and fourthAuthorizedRequestReturns429 in CardApplicationControllerTest.java under start/. Implement only TODO 7 as a per-authenticated-user and request-URI fixed-window limiter: allow three requests in 60 seconds and return 429 on the fourth with RateLimit-Limit, RateLimit-Remaining, Retry-After, and JSON error rate_limit_exceeded. Use thread-safe in-memory state, keep reset() clearing that state, skip anonymous and non-/api/ requests, and preserve the filter's position after AuthorizationFilter. Do not add a library, sleep in tests, weaken security, or change acceptance tests. State why this single-instance limiter is not production-ready, then run the focused rate-limit test.
```

```bash
mvn -Dtest=CardApplicationControllerTest#fourthAuthorizedRequestReturns429 test
```

## Human checkpoint

HTTP Basic and in-memory identities are teaching scaffolding, not the recommended production identity design. A real financial API would normally validate short-lived tokens from an identity provider. A production rate limiter needs shared state or gateway enforcement across service instances.
