# techContext.md

## Technologies Used
- Java 17 (via Maven toolchain)
- Selenium WebDriver (4.x)
- TestNG (7.x)
- Chrome browser (tested with chromedriver from user's specified local path)
- Maven (for dependency and build management)
- TestNG XML suite for parallel execution

## Development Setup
- Ensure Java 17 is installed and set in your environment.
- Download chromedriver and match to Chrome version; local path must match config in UserLoginTest.java.
- Run tests from project root with Maven (mvn test -DsuiteXmlFile=testng.xml).
- Screenshots require write permission to Pictures/Screenshots directory.

## Technical Constraints
- Chrome and chromedriver version compatibility must be maintained manually.
- Project hardcodes screenshot paths and chromedriver paths.
- No dynamic configuration or CI/CD integration yet (could be expanded later).

## Dependency Management
- pom.xml manages all dependencies: selenium-java, testng, and Maven plugins (compiler, surefire).
- No transitive exclusions needed; included versions verified for mutual compatibility.

## Tool Usage Patterns
- Use TestNG for organizing, running, and asserting test results.
- Use Maven for lifecycle, build, and executing tests.
- Use the memory-bank/ structure for technical and project documentation.
