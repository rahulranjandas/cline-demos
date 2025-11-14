# progress.md

## What Works

- Both "standard_user" and "problem_user" login flows are validated automatically.
- Screenshots are captured and saved for each test run, helping with post-run investigations.
- Tests execute in parallel using TestNG suite, reducing feedback loop times.
- All dependencies (Selenium, TestNG, Maven plugins) are current and compatible with Java 17.
- Documentation in memory-bank/ provides full project knowledge and onboarding support.

## What's Left to Build

- Expand coverage to other user types or negative login scenarios (lockout, etc) as needed.
- Introduce dynamic configuration, CI/CD integration, or test reporting if project scope expands.
- Adopt advanced design patterns (Page Object, etc) for larger/more complex suites.

## Current Status

- Project is stable and fully functional for defined scope.
- Actively incorporates feedback for new capabilities (concurrency, memory bank, modular docs).

## Known Issues

- All test parameters (paths, user credentials) are hardcoded.
- No mocks/stubs for the external system—tests run against live site.
- No central .env or config file for quick environment tweaks.

## Decision & Evolution Log

- Updated to Java 17 and parallel test execution on user request.
- Memory bank structure established per .clinerules/memory-bank.md, providing persistent project context.
- Handling read/write and platform-specific issues in a mixed OS environment (Windows/Linux tools) as needed.