# productContext.md

## Why This Project Exists
Manually verifying login for multiple user roles on https://www.saucedemo.com is error-prone and time consuming.

## Problems Solved
- Ensures each supported user role can access the application as expected.
- Detected access/screenshot issues are flagged immediately in CI.
- Automated results and artifacts (screenshots) enable rapid debugging.

## How It Should Work
- Automated scripts enter credentials for each user type.
- Test NG assertions confirm navigation to post-login area.
- Each login attempt automatically produces a post-login screenshot, saved by username.

## User Experience Goals
- Enable quick diagnostics with self-explanatory test failures and captured screenshots.
- Foster confidence that real-world login workflows are protected against regression.
- Provide a test automation pattern that is modular and extensible for new flows or users.
