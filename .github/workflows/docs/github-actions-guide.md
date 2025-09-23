# docs/github-actions-guide.md
# GitHub Actions Workflow Guide

This guide explains the GitHub Actions workflow for running Selenium WebDriver tests.

## File Location
- The workflow file is located at: `.github/workflows/maven.yml`

## Workflow Triggers
The workflow runs when:
- Code is pushed to the `main` branch
- A pull request is opened targeting the `main` branch

## Jobs and Steps

### 1. Checkout Code
Beginner: This checks out your repository code so the workflow can access it. 
Expert: Uses the official GitHub Action to clone the repository at the specified commit.
```yaml
- uses: actions/checkout@v3
```

### 2. Set Up Java
Beginner: Installs Java 11 and sets up Maven cache for faster builds. 
Expert: Uses Temurin (Adoptium) JDK distribution and caches Maven dependencies to speed up subsequent runs.
```yaml
- name: Set up JDK
  uses: actions/setup-java@v3
  with:
    java-version: '11'
    distribution: 'temurin'
    cache: 'maven'
```
### 3. Browser Setup (Chrome)
Beginner: Installs Chrome and ChromeDriver for Selenium tests. 
Expert: Uses a community action to install the latest ChromeDriver version compatible with the installed Chrome browser.
```yaml
- name: Set up WebDriver
  uses: nanasess/setup-chromedriver@v1
  if: matrix.browser == 'chrome'
  with:
    chromedriver-version: 'latest'
```
### 4. Browser Setup (Firefox)
Beginner: Installs Firefox for cross-browser testing. 
Expert: Uses an action specifically designed for Firefox setup in GitHub Actions.
```yaml
- name: Set up Firefox
  uses: browser-actions/setup-firefox@v1
  if: matrix.browser == 'firefox'
```
### 5. Run Tests
Beginner: Runs Maven tests with the specified browser tag. 
Expert: Uses Maven's batch mode (-B) and passes the browser type as an environment variable.
```yaml
- name: Run tests with Maven
  run: mvn -B test -Dcucumber.filter.tags="@${{ matrix.browser }}"
  env:
    BROWSER: ${{ matrix.browser }}
```
### 6. Upload Test Reports
Beginner: Saves test reports as build artifacts for 5 days. 
Expert: Uses GitHub's artifact storage to persist test results and reports between workflow runs.

#### Matrix Strategy
The workflow uses a matrix strategy to run tests against multiple browsers:
* Chrome
* Firefox

#### Environment Variables
* BROWSER: Specifies which browser to use for tests
* GITHUB_TOKEN: Automatically provided by GitHub for authentication

#### Viewing Results
* Go to the "Actions" tab in your GitHub repository
* Click on a workflow run to see the execution details
* Download the "test-reports" artifact to view detailed test results

#### Customization Options
* Test Tags: Modify the -Dcucumber.filter.tags parameter to run specific test tags
* Java Version: Update the Java version in the matrix.java section if needed
* Browsers: Add or remove browsers from the matrix.browser section
* Artifact Retention: Adjust the retention-days value as needed
```yaml
- name: Upload test reports
  uses: actions/upload-artifact@v3
  with:
    name: test-reports-${{ matrix.browser }}
    path: |
      target/cucumber-reports/**
      target/surefire-reports/**
    retention-days: 5
```

