# Step 1: Design the evolution

**Time budget:** 7 minutes

## Problem

A credit union has a legacy member-facing card eligibility response. Consumers now need a traceable decision, while underwriters need additional decision details. The legacy contract cannot disappear without notice.

## Record these decisions before coding

1. **Audience:** `v1` and `v2` are member-facing authenticated APIs. `v3` is an internal underwriter API.
2. **Sensitive fields:** annual income, monthly debt, requested limit, and credit score must not be logged or echoed unnecessarily.
3. **Resource and method:** use `POST /api/v{n}/card-applications/decisions` because the request contains a body and asks the service to evaluate an application.
4. **Breaking change:** `v1` returns `eligible`; `v2` replaces that shape with `decision`, `decisionId`, `approvedLimit`, and `reasons`. Existing consumers cannot parse it unchanged.
5. **Versioning challenge:** `v3` adds fields and changes the intended audience. Decide whether this justifies a version or whether a separate internal resource would be cleaner. The lab keeps `v3` so you can evaluate that trade-off.
6. **Retirement:** keep `v1` functional, announce deprecation, publish a sunset date, and link to its successor before removal.

## Copy-paste LLM prompt

```text
Act as an API design reviewer, not a code generator. Read README.md and the model/controller files under start/src. Create a compact table comparing the v1, v2, and v3 card-application decision contracts. For each transition, classify the change as breaking, non-breaking, or a separate-audience concern and explain why an existing consumer would or would not keep working unchanged. Identify sensitive fields that must not be logged or returned unnecessarily. Stay within the course topics: REST semantics, audience, contracts, versioning, deprecation, security, rate limiting, OpenAPI, and automated tests. Do not edit files yet.
```

## Human checkpoint

Do not accept “new feature means new version.” A new endpoint or optional response field can be additive. The learner must identify the exact consumer contract that breaks.
