# Step 4: Test and deliver

**Time budget:** 15 minutes

## Run the CI-ready gate

```bash
mvn test
```

Seven tests must pass without a running server, network call, database, or sleep:

1. `v1RemainsAvailableWithRetirementHeaders`
2. `v2AddsATraceableDecisionContract`
3. `v3AddsUnderwritingDetails`
4. `missingCredentialsReturn401`
5. `memberCannotCallUnderwriterOnlyV3`
6. `invalidCreditScoreReturns400`
7. `fourthAuthorizedRequestReturns429`

## Debug with evidence

If a test fails, give the coding assistant the exact failure and the smallest relevant source file. Do not ask it to rewrite the application.

### Copy-paste LLM repair prompt

```text
The capstone acceptance suite has this exact failure:

<PASTE ONE FAILURE HERE>

Read the failing test and only the production file on its request path. Identify the root cause by comparing expected status, headers, and JSON fields with the implementation. Propose the smallest correction that preserves all public paths, model records, security order, tests, and dependencies. Do not delete or weaken assertions. Apply the correction, run that one test, and only then run mvn test. Report both results and name the acceptance criterion the correction restored.
```

## Inspect the generated contract

Start the application after the tests pass:

```bash
CAPSTONE_MEMBER_PASSWORD=member-pass \
CAPSTONE_UNDERWRITER_PASSWORD=underwriter-pass \
mvn spring-boot:run
```

Open `http://localhost:8080/swagger-ui.html` or fetch `http://localhost:8080/api-docs`. Confirm:

- Three card-application operations appear.
- `v1` is marked deprecated.
- The Basic authentication scheme is present.
- The request and response records are represented as schemas.

Stop the application with `Ctrl+C`.

## Share-out evidence

Prepare a 30-second report:

1. One change that forced a new contract version.
2. One change that could have remained non-breaking.
3. One difference among `401`, `403`, and `429` observed in a test.
4. One correction made to LLM-generated code after reviewing a failed assertion.

## Stretch prompt

```text
All seven capstone tests pass. Add one focused MockMvc test, without changing production behavior, for either a DECLINED application or a REVIEW application. Derive the input from the documented policy, assert the decision and reason code, preserve test isolation, and run the new test plus mvn test. Explain which branch boundary the test protects.
```
