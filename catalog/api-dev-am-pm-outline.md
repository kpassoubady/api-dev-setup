# Fundamentals of API Development (4 Sessions × 4 Hours, Delivered Across 4 Days, AM/PM)

Duration: 4 Sessions × 4 Hours (16 hours total, split across 4 days, AM and PM cohorts)

Audience: Software developers who need the fundamentals of API design and development

Track: Java (Spring Boot). Hands-on labs are built against a Spring Boot REST API, tested with JUnit5 and Postman, using IntelliJ as the IDE.

Prerequisites:
- Basic Java familiarity (classes, interfaces, collections)
- IntelliJ, a JDK, and Postman installed before class
- No prior API design experience required

## 🗓️ Delivery Model: 1 Course, 2 Cohorts, 4 Days

This is authored as a single 16-hour course, but delivered as four 4-hour sessions on four separate days so two cohorts (morning and afternoon) can each go through the full course without doubling instructor prep:

| | Day 1 | Day 2 | Day 3 | Day 4 |
|---|---|---|---|---|
| **Morning Cohort** | Session 1 (AM) | Session 2 (AM) | Session 3 (AM) | Session 4 (AM) |
| **Afternoon Cohort** | Session 1 (PM, repeated) | Session 2 (PM, repeated) | Session 3 (PM, repeated) | Session 4 (PM, repeated) |

Each cohort attends one slot per day (AM or PM) and follows Session 1 through Session 4 across the four days, a 4-day course for that cohort. The instructor delivers the same session content twice each day, once for the AM cohort and once for the PM cohort.

## 🎯 Course Overview

This course teaches the fundamentals of API design and development: what APIs are and why they matter, how REST/SOAP/RPC protocols differ, how to write and document API contracts, how to manage an API's lifecycle and secure it, and how APIs fit into a modern DevOps ecosystem. Hands-on labs use a Spring Boot REST API as the shared example, with Postman for exploring/testing endpoints, JUnit5 for automated tests, and IntelliJ as the development environment throughout.

## 🧭 Main Topics Covered

- APIs: Purpose, Nomenclature & Types
  - Benefits and drawbacks of APIs, key terms, API types (Open, Partner, Internal)
- API Protocols & RESTful Basics
  - REST, SOAP, RPC overview; standards (HTTP methods, status codes, resource naming)
- API Contracts & Documentation
  - OpenAPI/Swagger standards; documenting APIs and common tooling
- API Lifecycle & Versioning
  - Common lifecycle approaches, pros/cons, non-breaking version strategies
- Security for APIs
  - Authentication, authorization, rate limiting
- DevOps & the API Ecosystem
  - API First strategy, CI/CD, automated testing, security within the ecosystem

## 📚 Learning Outcomes

- Explain the purpose of APIs and define key terms in API development
- Differentiate between API types and API protocols
- Build contracts into APIs and describe RESTful standards
- Discuss the API lifecycle and design a non-breaking versioning strategy
- Understand why security matters for APIs and configure basic security rules
- Describe how APIs fit into a DevOps/CI/CD ecosystem

## 🛠️ Project Context

Labs build on a shared Spring Boot REST API example, extended lab by lab. You'll work in IntelliJ as the IDE, explore and test endpoints in Postman, write automated tests in JUnit5, and use a Swagger/OpenAPI editor for the contract and documentation labs.

## ⏰ Day 1 (4 Hours): API Purpose, Types & Protocols

Delivered once for the morning cohort and once (repeated) for the afternoon cohort.

### 1. Welcome, Learner Introductions & Course Objectives

Course arc preview: what's covered across the 4 days.

### 2. APIs: Purpose, Nomenclature & Types
- APIs: purpose and nomenclature
- Benefits and drawbacks of APIs
- Key terms in API development
- API types: Open, Partner, Internal

### Kahoot 1

### Bio Break

### 3. API Protocols Overview: REST, SOAP & RPC

### 4. Breakout 1: Lab 1.0: Classifying API Audience Types
- Slides: `day1/slides/04-breakout-lab-1-0.md`
- Classify every endpoint in the quickstart API by audience type (Open, Partner, Internal)
- Identify which response fields are safe for each audience, and discover what undocumented endpoints reveal

### Bio Break

### 5. RESTful APIs: The Basics
- Drawbacks of not having standards
- Common standards: HTTP methods, status codes, resource naming

### 6. Breakout 2: Lab 1.1: Analyzing an Existing API
- Slides: `day1/slides/06-breakout-lab-1-1.md`
- Use Postman to send requests against an existing API and inspect its responses, identifying its protocol and standards adherence
- Stretch goals: compare a second API against the same checklist; document where it deviates from REST conventions

### Bio Break

### Kahoot 2

### 7. Discussion & Buffer: API Types/Protocols Deep-Dive, Q&A
- Open discussion on API types and protocol trade-offs from Labs 1.0 and 1.1
- Flex time: catch-up room for cohorts running behind, or extended Lab 1.1 stretch goals

### 8. Day 1 Wrap-up & Day 2 Preview

Day 1 Deliverable: Working understanding of API purpose, types, and protocols; a completed protocol/standards analysis of an existing API (Lab 1.1).

## ⏰ Day 2 (4 Hours): API Contracts, Documentation & Design

Delivered once for the morning cohort and once (repeated) for the afternoon cohort. Continuation of Day 1 for the same cohort.

### 1. Day 2 Kickoff & Day 1 Recap

### 2. API Contracts: Standards & OpenAPI/Swagger
- Drawbacks of not having standards
- Summary of common standards (OpenAPI/Swagger)

### Kahoot 1

### Bio Break

### 3. Breakout 1: Lab 1.2: Writing an OpenAPI Specification
- Write an OpenAPI specification for a basic resource
- Stretch goals: add request/response examples and error schemas

### Bio Break

### 4. Documenting APIs: Standards & Tools
- Drawbacks of not having standards
- Summary of common standards and tools

### 5. Breakout 2: Lab 1.3: Peer Review & Interactive Docs
- Peer review of API contracts
- Generate interactive documentation (Swagger UI)
- Stretch goals: annotate edge cases; add auth documentation to the spec

### Bio Break

### Kahoot 2

### 6. Design Lab Discussion: Contract-First vs. Code-First
- Comparing contract-first and code-first workflows using Postman/Swagger tooling
- Group review of Lab 1.2/1.3 outputs

### 7. Day 2 Wrap-up & Day 3 Preview

Day 2 Deliverable: An OpenAPI specification for a basic resource, peer-reviewed, with generated interactive documentation (Labs 1.2-1.3).

## ⏰ Day 3 (4 Hours): API Lifecycle & Security

Delivered once for the morning cohort and once (repeated) for the afternoon cohort. Continuation of Day 2 for the same cohort.

### 1. Day 3 Kickoff & Day 2 Recap

### 2. API Lifecycle: Overview & Versioning
- API lifecycle overview
- API versioning
- Review of most common approaches to lifecycle (pros and cons)

### Kahoot 1

### Bio Break

### 3. Breakout 1: Lab 2.1: Designing a Non-Breaking Version Strategy
- Design a non-breaking version strategy for an evolving API
- Stretch goals: draft a deprecation/sunset policy; handle a breaking-change edge case

### Bio Break

### 4. Security for APIs: Standards
- Drawbacks of not having standards
- Common standards: authentication, authorization, rate limiting

### 5. Breakout 2: Lab 2.2: Implementing Basic API Security Rules
- Implement or configure basic API security rules on the Spring Boot API
- Stretch goals: add rate-limit tiers; validate auth flows with a Postman collection

### Bio Break

### Kahoot 2

### 6. Lifecycle & Security Standards Deep-Dive, Q&A
- Drawbacks of skipping standards for both lifecycle and security, with real-world examples
- Group review of Lab 2.1/2.2 outputs

### 7. Day 3 Wrap-up & Day 4 Preview

Day 3 Deliverable: A non-breaking versioning strategy (Lab 2.1) and configured basic security rules (Lab 2.2) on the shared API.

## ⏰ Day 4 (4 Hours): DevOps, API Ecosystem & Capstone

Delivered once for the morning cohort and once (repeated) for the afternoon cohort. Continuation of Day 3 for the same cohort.

### 1. Day 4 Kickoff & Day 3 Recap

### 2. DevOps in API Development: Overview

### Kahoot 1

### Bio Break

### 3. API Ecosystem
- API First strategy
- DevOps (CI/CD, automated testing)
- API security within the ecosystem
- The case for deep knowledge

### Bio Break

### 4. Breakout 1: Lab 2.3: Simulating an Automated API Test Suite
- Build a JUnit5 test suite for the Spring Boot API, run from IntelliJ
- Stretch goals: add negative test cases; integrate a Postman collection runner

### Kahoot 2

### Bio Break

### 5. Breakout 2: Lab 2.4: Capstone Exercise

Capstone exercise integrating design, documentation, versioning, security, and testing from Days 1-4.

### 6. Review of Capstone Solutions & Discussion

### 7. Course Recap, Final Q&A & Next Steps

Day 4 Deliverable: Completed capstone integrating contract design, documentation, versioning, security, and an automated JUnit5 test suite.

## 🕒 Session Breakdown Table

### Day 1 (4 hours / 240 min)

| Topic | Duration |
|---|---|
| Welcome, Learner Introductions & Course Objectives | 15 min |
| APIs: Purpose, Nomenclature & Types | 35 min |
| Kahoot 1 | 10 min |
| Bio Break | 15 min |
| API Protocols Overview: REST, SOAP & RPC | 30 min |
| Breakout 1: Lab 1.0: Classifying API Audience Types | 15 min |
| Bio Break | 10 min |
| RESTful APIs: The Basics | 30 min |
| Breakout 2: Lab 1.1: Analyzing an Existing API | 30 min |
| Bio Break | 10 min |
| Kahoot 2 | 10 min |
| Discussion & Buffer: API Types/Protocols Deep-Dive, Q&A | 20 min |
| Day 1 Wrap-up & Day 2 Preview | 10 min |
| **Total** | **240 min** |

### Day 2 (4 hours / 240 min)

| Topic | Duration |
|---|---|
| Day 2 Kickoff & Day 1 Recap | 15 min |
| API Contracts: Standards & OpenAPI/Swagger | 35 min |
| Kahoot 1 | 10 min |
| Bio Break | 20 min |
| Breakout 1: Lab 1.2: Writing an OpenAPI Specification | 25 min |
| Bio Break | 10 min |
| Documenting APIs: Standards & Tools | 35 min |
| Breakout 2: Lab 1.3: Peer Review & Interactive Docs | 25 min |
| Bio Break | 10 min |
| Kahoot 2 | 10 min |
| Design Lab Discussion: Contract-First vs. Code-First | 25 min |
| Day 2 Wrap-up & Day 3 Preview | 20 min |
| **Total** | **240 min** |

### Day 3 (4 hours / 240 min)

| Topic | Duration |
|---|---|
| Day 3 Kickoff & Day 2 Recap | 15 min |
| API Lifecycle: Overview & Versioning | 35 min |
| Kahoot 1 | 10 min |
| Bio Break | 20 min |
| Breakout 1: Lab 2.1: Designing a Non-Breaking Version Strategy | 25 min |
| Bio Break | 10 min |
| Security for APIs: Standards | 35 min |
| Breakout 2: Lab 2.2: Implementing Basic API Security Rules | 25 min |
| Bio Break | 10 min |
| Kahoot 2 | 10 min |
| Lifecycle & Security Standards Deep-Dive, Q&A | 25 min |
| Day 3 Wrap-up & Day 4 Preview | 20 min |
| **Total** | **240 min** |

### Day 4 (4 hours / 240 min)

| Topic | Duration |
|---|---|
| Day 4 Kickoff & Day 3 Recap | 15 min |
| DevOps in API Development: Overview | 30 min |
| Kahoot 1 | 10 min |
| Bio Break | 20 min |
| API Ecosystem | 30 min |
| Bio Break | 10 min |
| Breakout 1: Lab 2.3: Simulating an Automated API Test Suite | 25 min |
| Kahoot 2 | 10 min |
| Bio Break | 10 min |
| Breakout 2: Lab 2.4: Capstone Exercise | 25 min |
| Review of Capstone Solutions & Discussion | 35 min |
| Course Recap, Final Q&A & Next Steps | 20 min |
| **Total** | **240 min** |

Combined Total Duration: 16 hours (960 minutes) across 4 sessions / 4 days, run twice per day for 2 cohorts.

## ✅ Deliverables (End of Course)

- Protocol/standards analysis of an existing API (Lab 1.1)
- OpenAPI specification for a basic resource, peer-reviewed, with interactive docs (Labs 1.2-1.3)
- Non-breaking versioning strategy (Lab 2.1)
- Configured basic API security rules (Lab 2.2)
- JUnit5 automated test suite for the shared Spring Boot API (Lab 2.3)
- Capstone integrating design, documentation, versioning, security, and testing (Lab 2.4)

## Teaching Philosophy

The course uses one Spring Boot API example throughout, so each lab builds on the last, tested with Postman and JUnit5 in IntelliJ.
