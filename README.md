# Java Todo Web App (Maven)

## Description
This is a comprehensive Java web application built using Maven, Servlets, and JSP to manage todo tasks. The application demonstrates best practices in Java web development, including MVC architecture, integration testing with Selenium, and embedded Tomcat for testing.

## Features
- Add new tasks via form submission
- View all tasks in a responsive list format
- Mark tasks as complete
- Clean and intuitive user interface
- Comprehensive integration test suite

## Project Structure
```
src/
├── main/
│   ├── java/
│   │   └── com/todoapp/
│   │       ├── controller/
│   │       │   └── TodoController.java    # Handles HTTP requests
│   │       └── model/
│   │           └── Todo.java              # Todo data model
│   └── webapp/
│       └── WEB-INF/
│           ├── todo.jsp                   # Main view template
│           └── web.xml                    # Web application configuration
└── test/
    └── java/
        └── com/todoapp/
            ├── config/
            │   └── TestServer.java        # Embedded Tomcat configuration
            └── integration/
                └── TodoIntegrationTest.java # Integration tests
```

## Technologies Used
- Java 11
- Maven (Build tool)
- Java Servlet API 4.0.1
- JSP (JavaServer Pages)
- JSTL 1.2
- JUnit 4.13.2
- Selenium WebDriver 4.10.0
- WebDriverManager 5.4.0
- Embedded Tomcat 9.0.76
- Maven Failsafe Plugin (Integration Testing)

## Setup Instructions

### Development Setup
1. Prerequisites:
   - Java JDK 11 or higher
   - Maven 3.6 or higher
   - Chrome browser (for integration tests)

2. Clone or extract this project:
   ```bash
   git clone <repository-url>
   cd TodoWebApp
   ```

3. Build the project:
   ```bash
   mvn clean package
   ```

### Running the Application
1. Deploy the WAR file:
   - Copy `target/TodoWebApp-1.0-SNAPSHOT.war` to your Tomcat webapps directory
   - Or use any Java servlet container

2. Access the application:
   ```
   http://localhost:8080/TodoWebApp/
   ```

### Running Tests
1. Run integration tests:
   ```bash
   mvn verify
   ```
   This will:
   - Build the application
   - Start an embedded Tomcat server
   - Run Selenium-based integration tests
   - Generate test reports

## Integration Testing
The project includes comprehensive integration tests using:
- Selenium WebDriver for browser automation
- Embedded Tomcat for standalone test execution
- WebDriverManager for automatic ChromeDriver management
- JUnit for test framework

Test cases cover:
- Homepage loading and validation
- Adding new todos
- Marking todos as complete

## Maven Commands
- `mvn clean`: Clean the project directory
- `mvn compile`: Compile the source code
- `mvn test`: Run unit tests
- `mvn verify`: Run integration tests
- `mvn package`: Create the WAR file
- `mvn clean verify`: Clean and run full test suite

## Notes
- Integration tests use random ports to avoid conflicts
- Embedded Tomcat is used only for testing
- Chrome browser is required for running integration tests