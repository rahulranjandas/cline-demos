# activeContext.md

## Current Work Focus
- Maintaining and evolving Selenium + TestNG Java automation for login validations.
- Extending project scope to include a JavaScript Calculator with UI (index.html) and calculator logic (js/Calculator.js).
- Using Selenium MCP server to automate and verify the calculator web app.
- Tracking project state and automation coverage through structured memory bank documentation.

## Recent Changes
- Added parallel/concurrent execution via TestNG.
- Incorporated both standard_user and problem_user coverage in Java tests.
- Added js/Calculator.js module for JavaScript-side addition logic.
- Created index.html for user-driven addition via browser.
- Automated Selenium-driven UI tests for both positive and negative number addition, using browser automation via MCP server.
- Generated, then updated, memory bank files per .clinerules/memory-bank.md instructions.

## Next Steps
- Expand testing for edge cases and input validation (e.g., decimals, empty input) in the JavaScript calculator.
- Update memory bank as new test features, dependencies, or structure evolves.
- Enhance UI/UX for the HTML calculator if required.
## Active Decisions & Considerations
- Use explicit ChromeDriver path instead of remote manager (per user direction).
- Validate login success by asserting 'inventory' page load after login.
- Save screenshots per user for debuggability.

## Implementation Patterns & Preferences
- TestNG lifecycle annotations (@BeforeClass, @AfterClass, @Test) for setup/teardown/test organization.
- Maven for build and dependency control.
- Markdown docs in memory-bank for unified project knowledge sharing.

## Learnings & Insights
- Structuring project documentation supports onboarding and maintainability.
- Parallelization with TestNG improves feedback speed.
