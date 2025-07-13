# TodoWebApp

A simple, modern Java web application for managing a todo list, built with Servlets, JSP, and Maven.

---

## Table of Contents
- [Project Overview](#project-overview)
- [Features](#features)
- [Code Structure](#code-structure)
- [How It Works](#how-it-works)
- [Setup & Installation](#setup--installation)
- [Running the App](#running-the-app)
- [Testing](#testing)
- [Technologies Used](#technologies-used)
- [Customization](#customization)
- [License](#license)

---

## Project Overview
This project demonstrates a basic Java web application using the Servlet API and JSP for the UI. The goal is to provide a minimal, easy-to-understand example of a CRUD (Create, Read) todo list, suitable for learning or as a starting point for more complex apps.

**What are we trying to achieve?**
- Show how to build a Java web app with Servlets and JSP.
- Demonstrate form handling, request/response cycles, and simple in-memory data storage.
- Provide a clean, modern UI for basic todo management.

---

## Features
- Add new todo items via a form.
- View all todos in a styled, centered list.
- Each todo has a "View" button (currently shows an alert; can be extended).
- Responsive, modern UI with custom CSS.

---

## Code Structure
```
TodoWebApp/
├── Jenkinsfile                  # (CI/CD pipeline config, if used)
├── pom.xml                      # Maven project file (dependencies, build config)
├── README.md                    # Project documentation
├── sonar-project.properties     # SonarQube config (optional)
└── src/
    ├── main/
    │   ├── java/
    │   │   └── com/
    │   │       └── todoapp/
    │   │           ├── controller/
    │   │           │   └── TodoController.java   # Main servlet controller
    │   │           └── model/
    │   │               └── Todo.java            # Todo item model (POJO)
    │   └── webapp/
    │       └── WEB-INF/
    │           ├── todo.jsp                    # Main JSP UI
    │           └── web.xml                     # Servlet configuration
    └── test/
        └── java/
            └── com/
                └── todoapp/
                    ├── config/
                    │   └── TestServer.java     # (Test server config, placeholder)
                    └── integration/
                        └── TodoIntegrationTest.java # (Integration test, placeholder)
```

---

## How It Works
- **Model:** `Todo.java` is a simple POJO representing a todo item (with a `task` string).
- **Controller:** `TodoController.java` is a `HttpServlet` that handles GET and POST requests:
  - `doGet`: Sets the current todo list as a request attribute and forwards to `todo.jsp`.
  - `doPost`: Reads the submitted task, adds it to the in-memory list, and redisplays the list.
- **View:** `todo.jsp` renders the UI, displaying todos and a form to add new ones. Each todo has a "View" button.
- **Servlet Config:** `web.xml` maps the controller to `/` and `/list` URLs.
- **Persistence:** Todos are stored in a static Java list (in-memory, resets on server restart).

---

## Setup & Installation

### Prerequisites
- Java 11 or higher
- Maven 3.x
- A Servlet container (e.g., Apache Tomcat 9+)

### Steps
1. **Clone the repository:**
   ```sh
   git clone <your-repo-url>
   cd TodoWebApp
   ```
2. **Build the project:**
   ```sh
   mvn clean package
   ```
   This will generate a `TodoWebApp.war` file in the `target/` directory.
3. **Deploy to Tomcat:**
   - Copy `target/TodoWebApp.war` to your Tomcat `webapps/` directory.
   - Start Tomcat (if not already running).
4. **Access the app:**
   - Open your browser and go to: [http://localhost:8080/TodoWebApp/](http://localhost:8080/TodoWebApp/)

---

## Running the App
- The home page displays the todo list and a form to add new todos.
- Enter a task and click "Add" to submit.
- Todos appear instantly in the list below, each with a "View" button.
- The UI is centered and styled for a modern look.

---

## Testing
- The `test/` directory contains placeholders for integration tests and test server config.
- You can add JUnit or Selenium tests as needed.
- To run tests:
  ```sh
  mvn test
  ```

---

## Technologies Used
- Java 11
- Servlet API 4.0.1
- JSP (JavaServer Pages)
- Maven
- Tomcat (for deployment)
- JUnit (for testing)
- Selenium (for UI testing, optional)
- JSTL (for JSP tag support)

---

## Customization
- **Persistence:** For real-world use, replace the in-memory list with a database (JDBC, JPA, etc).
- **Features:** Add edit/delete, user authentication, or REST API endpoints.
- **UI:** Extend the JSP or use a frontend framework.

---

## License
This project is for educational/demo purposes. Use and modify as you wish.