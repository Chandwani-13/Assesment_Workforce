# Workforce Management API

This is a backend assignment for Railse, where I implemented a workforce management API using Java 17 and Spring Boot. The system allows task creation, assignment, re-assignment, status tracking, priority handling, and comments, all using in-memory data structures.

---

## 📦 Tech Stack

- Java 17
- Spring Boot 3.0.4
- Gradle
- Lombok
- MapStruct
- In-memory data structures (`Map`, `List`, etc.)

---

## 📁 Project Structure

src/main/java/com/yourcompany/workforcemgmt/
├── WorkforcemgmtApplication.java
├── controller/
│ └── TaskController.java
├── service/
│ └── TaskService.java
├── model/
│ ├── Task.java
│ └── Staff.java
├── dto/
│ ├── TaskDto.java
│ └── CreateTaskRequest.java
└── mapper/
└── TaskMapper.java


---

## 🚀 How to Run the Project

1. Clone this repository:
   ```bash
   git clone https://github.com/your-username/workforce-mgmt-api.git
   cd workforce-mgmt-api
./gradlew bootRun
http://localhost:8080/tasks
```

## Features Implemented
 Bug Fixes
Reassignment Duplicate Bug: Reassigning a task now cancels the original task correctly.

Cancelled Tasks Hidden: Cancelled tasks are now excluded from task list views.

 New Features
Smart Daily Task View: View all active tasks in a date range and any ongoing ones before the range.

Task Priority: Set and update task priority (HIGH, MEDIUM, LOW). Also fetch by priority.

Comments & Activity Log: Add comments and view all task activities (created, reassigned, priority changed, etc.) in order.

```
POST /tasks
POST /tasks/assign-by-ref
GET /tasks/smart-daily?from=YYYY-MM-DD&to=YYYY-MM-DD
POST /tasks/{id}/priority?value=HIGH
GET /tasks/{id}

```

Jyotsana Chandwani
