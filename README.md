# Java Calculator – CI/CD Learning Project

A simple Java calculator built with Maven and JUnit 5, used to learn **CI/CD with GitHub Actions**.

## Features

- Operations: add `+`, subtract `-`, multiply `*`, divide `/`, modulo `%`, power `^`, square root
- Error handling for divide/modulo by zero and square root of negative numbers
- Interactive console mode and one-shot command-line mode
- Unit tests with JUnit 5 (nested + parameterized tests)
- Code coverage with JaCoCo
- GitHub Actions pipeline with a Java version matrix driven by `versions.env`

## Project Structure

```
Devops-Java-Project/
├── .github/workflows/ci.yml          # GitHub Actions pipeline
├── src/
│   ├── main/java/com/example/calculator/
│   │   ├── Calculator.java           # Calculator logic
│   │   └── CalculatorApp.java        # Entry point (main method)
│   └── test/java/com/example/calculator/
│       └── CalculatorTest.java       # Unit tests
├── target/                           # Build output (generated, git-ignored)
├── pom.xml                           # Maven build configuration
├── versions.env                      # Versions used by the CI pipeline
└── .gitignore
```

## Prerequisites

- JDK 17 or newer
- Maven 3.8+

Check your setup:

```bash
java -version
mvn -version
```

## Build, Test and Run

| Command | What it does |
|---|---|
| `mvn compile` | Compile source code into `target/classes` |
| `mvn test` | Compile and run unit tests |
| `mvn verify` | Compile, test, generate coverage and package the JAR |
| `mvn clean` | Delete the `target/` folder |

Run the app:

```bash
# Interactive mode
java -jar target/calculator-1.0.0.jar

# One-shot mode
java -jar target/calculator-1.0.0.jar 5 + 3
```

Reports after `mvn verify`:

- Test results: `target/surefire-reports/`
- Coverage: `target/site/jacoco/index.html`

## CI/CD Pipeline

The workflow in [.github/workflows/ci.yml](.github/workflows/ci.yml) runs on:

- Push to `main`
- Pull requests targeting `main`
- Manual trigger (**Actions** tab → **Java CI** → **Run workflow**)

### Jobs

1. **load-versions** – reads [versions.env](versions.env) and exposes the values as job outputs.
2. **build-and-test** – runs once per Java version in the matrix:
   - Checks out the code
   - Sets up the JDK (with Maven dependency caching)
   - Runs `mvn -B clean verify`
   - Uploads test and coverage reports as artifacts
   - Uploads the JAR (from the primary Java version only)

### Managing Versions

Edit [versions.env](versions.env) to change what CI uses – no workflow changes needed:

```env
JAVA_VERSIONS=17,21
PRIMARY_JAVA_VERSION=17
JAVA_DISTRIBUTION=temurin
JUNIT_VERSION=5.10.2
```

| Key | Purpose |
|---|---|
| `JAVA_VERSIONS` | Comma-separated Java versions for the test matrix |
| `PRIMARY_JAVA_VERSION` | Version whose JAR is uploaded as an artifact |
| `JAVA_DISTRIBUTION` | JDK vendor used by `actions/setup-java` |
| `JUNIT_VERSION` | Overrides `junit.version` in `pom.xml` |

> Keep comments on their own lines (starting with `#`); inline comments are not supported.

## Getting Started with GitHub

```bash
git init
git add .
git commit -m "Initial commit"
git branch -M main
git remote add origin https://github.com/<your-username>/<your-repo>.git
git push -u origin main
```

Then open the **Actions** tab in your repository to watch the pipeline run.

## Learning Exercises

1. Break a test on purpose, push, and see the pipeline fail.
2. Add a new operation (e.g. `percentage`) with tests.
3. Add `22` to `JAVA_VERSIONS` and watch the matrix grow.
4. Add a job that creates a GitHub Release when you push a tag like `v1.0.0`.
5. Add a status badge to this README:
   `![Java CI](https://github.com/<your-username>/<your-repo>/actions/workflows/ci.yml/badge.svg)`
