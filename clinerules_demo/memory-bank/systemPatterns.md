# systemPatterns.md

## System Architecture
- Java 17 + Maven + TestNG + Selenium WebDriver form the automation backbone.
- Tests reside in src/test/java/com/example for modular test management.
- Chrome browser testing automated using explicit local chromedriver paths.

## Key Technical Decisions
- Direct chromedriver path configuration ensures predictable local environment (no remote dependency).
- TestNG parallel execution at method level for optimal run time.
- Screenshots written to user-specific files for result traceability.

## Design Patterns Used
- Page Object Model (if expanded) is recommended, but current suite uses direct locators for clarity in focused login scenarios.
- Test classes use TestNG's annotations for lifecycle and organization.

## Component Relationships
- Each @Test method represents a workflow for a specific user role.
- Shared setUp and tearDown handle driver lifecycle for all included tests.

## Critical Implementation Paths
- setUp: initializes browser driver.
- test methods: perform login with credentials, navigate, and assert for success.
- Screenshot capture and file output: follows each login flow for verifiability.
- tearDown: ensures complete browser/process cleanup post-execution.
