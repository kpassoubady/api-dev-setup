# Welcome to Fundamentals of API Development!

To ensure a smooth and productive learning experience, please complete the setup before class.


## 📋 Course Overview

Review the course outline and schedule here: 📄 [Course Catalog - Fundamentals of API Development](https://github.com/kpassoubady/api-dev-setup/blob/main/catalog/api-dev-am-pm-outline.md)

This is a 16-hour course delivered as four 4-hour sessions across four days (morning and afternoon cohorts). You'll build hands-on skills in API design and development using a shared Spring Boot REST API example, with Postman for testing, JUnit5 for automated tests, and IntelliJ as the IDE throughout.


## 🛠️ Pre-Class Setup (Required)

Please complete the installation before the training session: 📄 [Installation Instructions](https://github.com/kpassoubady/api-dev-setup/blob/main/install/install.md)

| Category | Tools / Packages |
|----------|-------------------|
| **JDK** | OpenJDK 17+ (recommended: JDK 21) |
| **Build Tool** | Apache Maven 3.9+ |
| **IDE** | IntelliJ IDEA (Community or Ultimate) |
| **API Client** | Postman |
| **Version Control** | Git |

The installation guide includes OS-specific instructions for both Windows and macOS.

Once installed, use the [quickstart-project](https://github.com/kpassoubady/api-dev-setup/tree/main/quickstart-project) to verify everything works end to end. Open it in IntelliJ, run it with Maven, call its endpoints from Postman, and run its JUnit5 tests.


## ✅ Checklist Before Class

- [ ] JDK 17+ installed (`java -version`)
- [ ] Maven 3.9+ installed (`mvn -version`)
- [ ] Git installed (`git --version`)
- [ ] IntelliJ IDEA installed and opens `quickstart-project` as a Maven project
- [ ] Postman installed and able to send a request
- [ ] `mvn spring-boot:run` starts `quickstart-project` on <http://localhost:8080>
- [ ] `GET http://localhost:8080/api/v1/health` returns `{"status":"UP", ...}`
- [ ] `mvn test` passes all JUnit5 tests


## 📚 Quick Links

| Resource               | Link                                                                                                              |
| ---------------------- | ------------------------------------------------------------------------------------------------------------------ |
| Course Catalog         | [Fundamentals of API Development](https://github.com/kpassoubady/api-dev-setup/blob/main/catalog/api-dev-am-pm-outline.md) |
| Installation Guide     | [install.md](https://github.com/kpassoubady/api-dev-setup/blob/main/install/install.md)                          |
| macOS Install Guide    | [install-mac.md](https://github.com/kpassoubady/api-dev-setup/blob/main/install/install-mac.md)                  |
| Windows Install Guide  | [install-win.md](https://github.com/kpassoubady/api-dev-setup/blob/main/install/install-win.md)                  |
| Quickstart Project     | [quickstart-project](https://github.com/kpassoubady/api-dev-setup/tree/main/quickstart-project)                  |


## 🆘 Need Help?

If you encounter any issues during setup, please don't hesitate to reach out before the training day.


Looking forward to seeing you in class! 🚀
