# 🧪 Selenium Cucumber Java Automation Framework

Welcome to your next-gen test automation framework built with ❤️ using **Java**, **Selenium 4.13.0**, **Cucumber**, and **TestNG**.

> ⚙️ Designed for Web UI automation with best practices like Page Object Model, reusable utilities, and clean structure.

---

## 🚀 Tech Stack

- Java 🟦
- Selenium 4.13.0 🧪
- Cucumber 🥒
- TestNG ✅
- Maven 📦
- Page Object Model 🏗️
- Extent Reports 📊 (if added)

---

## 📁 Project Structure

```bash
📦selenium-cucumber-framework
├── src
│   ├── main
│   │   └── java
│   │       └── pages             # All POM classes
│   │       └── utils             # Reusable utility classes
│   ├── test
│   │   └── java
│   │       └── stepDefinitions   # Step definitions
│   │       └── runners           # TestNG runner classes
│   │       └── features          # Cucumber feature files
├── pom.xml
├── testng.xml
└── README.md

🔧 How to Run
1. Clone the repo:
git clone https://github.com/dinesh-reddy-ys/your-repo-name.git
cd your-repo-name

2. Run tests via Maven:
   mvn test
or via TestNG
   mvn clean test -DsuiteXmlFile=testng.xml


🌱 Getting Started
Prerequisites
-JDK 11 or later
-Maven
-Any IDE (IntelliJ / Eclipse)
-Chrome browser (or add other drivers as needed)

Setup
Install Maven dependencies:
mvn clean install

-Configure the config.properties file for browser, environment, etc.

✅ Features
 - BDD with Cucumber

 - Page Object Model structure

 - Custom WebDriverManager

 - File download validation 🔽

 - Dynamic Web Table validation 🧮

 - Detailed logging and reporting

 - Jenkins integration (coming soon...)

 - Docker support (coming soon...)




