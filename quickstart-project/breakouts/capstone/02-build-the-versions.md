# Step 2: Build the three contracts

**Time budget:** 18 minutes

Open `start/src/main/java/com/apidev/quickstart/service/CardDecisionService.java` and `controller/CardApplicationController.java`. Complete TODOs 1-5 without changing the model records or tests.

## Decision policy

Use these deterministic classroom rules:

- `v1` is eligible when `creditScore >= 660`; otherwise it is not eligible.
- Debt-to-income ratio is `monthlyDebt / (annualIncome / 12)`, rounded to two decimals.
- `DECLINED`: credit score below `620` or ratio above `0.45`.
- `REVIEW`: credit score below `680` or ratio above `0.36`, after decline rules.
- `APPROVED`: all other valid applications.
- Approved limit is the smaller of requested limit and `20%` of annual income, rounded to two decimals.
- A non-approved application receives an approved limit of zero.
- Risk tier is `LOW` at `740+`, `MODERATE` at `680-739`, and `HIGH` below `680`.

## Version contracts

- `v1`: `eligible`, `approvedLimit` plus retirement headers.
- `v2`: `decisionId`, `decision`, `approvedLimit`, `reasons`.
- `v3`: all `v2` concepts plus `debtToIncomeRatio`, `riskTier`, and `apiVersion`.

For `v1`, keep the endpoint available and add:

```text
Deprecation: @1793491200
Sunset: Thu, 31 Dec 2026 23:59:59 GMT
Link: </api/v2/card-applications/decisions>; rel="successor-version"
```

## Copy-paste LLM prompt

```text
I am completing a Java 21 and Spring Boot 3.2.5 capstone. Read README.md, 02-build-the-versions.md, CardDecisionService.java, CardApplicationController.java, all three model records, and CardApplicationControllerTest.java under start/. Implement only TODOs 1-5. Preserve package names, public signatures, paths, validation annotations, tests, and four-space formatting. Use deterministic BigDecimal calculations and HALF_UP rounding. Do not add dependencies, persistence, external calls, logging of request data, or new endpoints. Keep v1 operational and add the exact Deprecation, Sunset, and Link headers from the guide. Explain how each implementation matches an acceptance test, then run only the first three relevant tests and report failures without changing tests.
```

Run the contract checks:

```bash
mvn -Dtest=CardApplicationControllerTest#v1RemainsAvailableWithRetirementHeaders test
mvn -Dtest=CardApplicationControllerTest#v2AddsATraceableDecisionContract test
mvn -Dtest=CardApplicationControllerTest#v3AddsUnderwritingDetails test
```

## Human checkpoint

Review generated code for rule ordering. A request that meets a decline rule must not be changed to `REVIEW` by a later branch. Confirm money and ratios use `BigDecimal`, not binary floating-point arithmetic.
