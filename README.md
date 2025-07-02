# 🧪 User Management API Test Suite

This project demonstrates automated API testing using **Rest Assured** and **TestNG**, complete with detailed execution reporting via **Allure**. It targets the mock [Reqres API](https://reqres.in) to simulate a user management workflow.

---

## ✅ Features

- Comprehensive test coverage for Create, Read, Update, and Delete (CRUD) operations
- REST assertions with status codes and JSON payload validation
- TestNG orchestration with logical test order and grouping
- Allure reporting with severity levels, step annotations, and response attachments
- Easy to extend for real APIs, mock servers, or CI pipelines

---

## 🛠️ Tech Stack

| Tool               | Purpose                                |
|--------------------|----------------------------------------|
| **Rest Assured**   | Fluent Java library for REST API testing |
| **TestNG**         | Testing framework with priority and suite control |
| **Allure**         | Beautiful, interactive test reporting  |
| **Maven**          | Build tool and dependency manager      |
| **Reqres**         | Public mock REST API used for simulation |

---

## 📁 Project Structure

```bash
.
├── pom.xml                          # Maven dependencies and test plugins
├── README.md                        # Project documentation and usage
├── target/                          # Compiled output and Allure result files
│   └── allure-results/              # Allure test run results
└── src/
    └── test/
        └── java/
            └── tests/
                └── UserMangementTest.java   # TestNG test suite with Allure annotations
        └── resources/
            └── allure.properties            # Configurable test parameters like baseURI
```

---

## 🚀 How to Run Tests

Make sure you have JDK + Maven + Allure CLI installed.

1. Install dependencies and build the project:
   ```bash
   mvn clean install
   ```

2. Execute the tests:
   ```bash
   mvn test
   ```

3. Generate and launch the Allure report:
   ```bash
   allure serve target/allure-results
   ```

---

## 🔎 Notes

- A static user ID (`userId = 2`) is used due to the non-persistent nature of [reqres.in](https://reqres.in).
- Each test logs its response and attaches it directly to the Allure report.
- The test suite is annotated with `@Epic`, `@Feature`, `@Story`, and `@Severity` for rich reporting and filtering.

---

## 📎 GitHub Repository

🔗 **[GitHub Repo – User Management API Tests](https://github.com/your-username/your-repo-name)**  
> Includes full source code, a ready-to-use Maven project, and complete documentation.

---

## 👨‍💻 Author

**Omar**  
QA Engineer | Automation Specialist | API Explorer  
Crafted with precision and test-driven passion 🚀

---

## 🙌 Contributions & Suggestions

Feel free to fork, clone, or raise a PR! Ideas to extend this suite with:
- Dynamic test data
- JWT-based authorization
- Mock servers (e.g. json-server, WireMock)
- CI/CD integration with GitHub Actions or Jenkins

are always welcome!
