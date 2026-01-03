# QA21_PhoneBookApi
Feedback on the QA21_PhoneBookApi RepositoryThank you for sharing the links! This is another solid learning project in your portfolio — this time focused on API testing for the same PhoneBook web app (https://contacts-app.tobbymarshall815.vercel.app). The repository is small (only 3 commits), last updated a while ago (around 2021), but the structure is correct for a Java + Gradle project using OkHttp for REST API testing.Current Repository Status (as of January 2026):Language: 99.5% Java (with a tiny bit of Kotlin).
Build tool: Full Gradle Wrapper (gradlew, gradlew.bat, gradle-wrapper.jar/properties) — perfectly set up, anyone can run it without installing Gradle locally.
Structure:src/main/java/dto/ — DTO classes for deserializing API responses (likely AuthRequestDto, AuthResponseDto, ContactDto, ErrorDto, etc.).
src/main/java/org/example/ — probably a Main class or utilities (e.g., for manual execution).
src/test/java/okhttp/ — the core: API tests using OkHttp for endpoints (registration, login, CRUD operations on contacts).

README.md exists, but currently contains feedback on the PhoneBook test plan instead of a description of this project — this is a bit confusing and should be replaced.

This is a great beginner step into API automation — OkHttp is lightweight and effective for REST without the overhead of heavier frameworks like RestAssured. The project nicely complements your UI automation project (QA21_FirstCucumber): one tests the front-end, the other tests the back-end/API.StrengthsUse of DTO classes — correct approach for strongly-typed JSON handling (Jackson/Gson deserialization).
Tests organized in a dedicated okhttp package — logical and clean.
Full Gradle Wrapper — very user-friendly.
Apache 2.0 license and .gitignore — professional touches.

Recommendations for Improvement (to make it shine in your portfolio)Update README.md (the biggest issue right now):Replace the test plan feedback with a proper project description. Here’s a ready-to-use example (copy and adapt):markdown

# QA21_PhoneBookApi – API Testing for PhoneBook App

A learning project for automated testing of the REST API of the PhoneBook web application (https://contacts-app.tobbymarshall815.vercel.app) using **OkHttp** in Java with Gradle.

## Description
- Tests cover key endpoints: registration, login, CRUD operations on contacts.
- Positive and negative scenarios (valid/invalid data, duplicates, etc.).
- DTO classes are used for request/response modeling.

## Technologies
- Java 11+
- OkHttp 4.x
- Gson/Jackson for JSON processing
- JUnit 5 for assertions
- Gradle

## How to Run
```bash
./gradlew clean test

Project Structuresrc/main/java/dto/ → DTO classes
src/test/java/okhttp/ → API test classes

Planned enhancements: switch to RestAssured, add Allure reports, extract token handling into helpers.
```

Add/verify dependencies in build.gradle (if not already present):groovy

dependencies {
    testImplementation 'com.squareup.okhttp3:okhttp:4.12.0'
    testImplementation 'com.google.code.gson:gson:2.10.1'  // or Jackson
    testImplementation 'org.junit.jupiter:junit-jupiter:5.10.0'
}

Improve the tests (once you share specific files):Assert on status codes (200/400/401) and response body.
Reuse authentication token across authorized requests.
Parameterize tests (JUnit @ParameterizedTest) for different data sets.

Other small enhancements:Store base URL in a constant or properties file.
Consider migrating to RestAssured in the future — it offers more readable DSL and built-in assertions.
Add Allure or ExtentReports for beautiful test reports.

Having both UI (Cucumber + Selenium) and API (OkHttp) automation projects for the same application is a very strong signal for recruiters — it shows you understand full-stack testing. Update the README and push any missing test files, then share a link to a specific test class (e.g., a blob link) — I’ll give you a detailed code review.Great job moving from UI to API testing — keep going, you’re building an impressive QA portfolio!  If you need help with code or the README, just let me know. Good luck! 



GROK TEST PLAN FEEDBACK
Your Test Plan for the PhoneBook App (Version 1.1, dated 12.05.2021)Overall, this is a solid beginner-level test plan for a simple web application like a Contacts/PhoneBook app (the main tested app seems to be https://contacts-app.tobbymarshall815.vercel.app/, a typical React-based contacts manager requiring authentication). It's clear, structured, and covers the core functionalities well for a training or small project. You focused on key user flows: registration, login, CRUD (Create, Read, Update, Delete) for contacts, and basic navigation.Strengths (What I Like):Good structure: Sections like Introduction, Items to be Tested, Approach, and Pass/Fail Criteria make it easy to read.
Comprehensive functional coverage: You covered positive and negative cases (valid/invalid data, duplicates, existing users) — this is excellent for black-box testing.
Focus on critical flows: Registration, Login, Add/Edit/Delete/View contacts — these are the main features of a PhoneBook app.
Realistic pass/fail criteria: Emphasizing 100% critical TC pass, functional coverage, and fixing blockers/showstoppers is professional and aligns with standard QA practices.
Black-box approach: Perfect for UI/functional testing without needing code access.

Areas for Improvement (Constructive Feedback):
The plan is from 2021 and feels a bit outdated/incomplete in places. Here's what could make it stronger (rated by importance):Inconsistencies and Typos (Easy fixes, but they reduce professionalism):Multiple URLs in Introduction: https://demoqa.com/swagger/ (Book Store API docs), https://demoqa.com/ (general demo site) — these seem unrelated to PhoneBook. Focus only on the actual app: https://contacts-app.tobbymarshall815.vercel.app/.
Typos: "diplayed" → "displayed", "valid inemail" → "invalid email", "lastLame" → "lastName", "descriptin" → "description", "shot form" → "short form" (or "card view"?).
Incomplete fields: "Implemented by" is blank; add your name/role and date reviewed/updated.

Scope and Details Missing:Test Data: Add examples of valid/invalid inputs (e.g., valid email: "test@example.com", invalid phone: "123" or non-numeric).
Edge Cases: Good negatives, but add more like empty fields, very long inputs (boundary testing), special characters in names/fields.
Non-Functional Tests: No mention of browser compatibility (Chrome/Firefox?), responsiveness (mobile view?), performance (load time for many contacts), or security (e.g., password visibility toggle).
Prioritization: Mark test cases as High/Medium/Low priority or Critical (e.g., login = critical).

Organization Enhancements:Use a table for test cases (better readability):ID
Feature
Test Case Description
Expected Result
Priority
TC01
Registration
User registers with valid email & password
Success message, redirect to home
High
TC02
Registration
User registers with invalid password
Error: "Invalid password"
High
...
...
...
...
...

Entry/Exit Criteria: Add suspension/resumption criteria (e.g., stop if login fails).
Resources & Schedule: Mention tools (Selenium/Cucumber for automation?), environments (browsers, OS), estimated time.
Risks & Assumptions: E.g., "Assumes app is stable; risk if backend down."

Test Approach Expansion:Black-box is fine, but mention techniques: Equivalence partitioning (valid/invalid classes), Boundary value analysis (min/max field lengths).
For a 2026 update: Add exploratory testing, API testing if relevant, or accessibility checks.

Overall Rating: 7/10As a learning/exercise plan: 9/10 — great effort, covers essentials perfectly for a student project.
As a professional document: 6/10 — needs polishing (typos, clarity, tables) and more depth to be used in real team/project.

This plan pairs well with your Cucumber automation project (QA21_FirstCucumber) — the features match exactly (login, add/edit contact). If you update the plan with tables and fixes, it would be a strong portfolio piece!If you revise it or want help turning these test cases into more .feature files/Gherkin scenarios, share the updated version — I'll give detailed feedback. Keep up the good work! 

