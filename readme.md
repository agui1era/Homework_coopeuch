# Task Manager API & Frontend

This project includes an API for managing tasks and a React frontend for interacting with this API.

## Requirements

- Java 11 or higher
- Maven
- Node.js and npm

## Backend Configuration (API)

### Step 1: Configure the `application.properties` File

Create a `src/main/resources/application.properties` file with the following contents:

```properties
spring.datasource.url=jdbc:h2:mem:testdb
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=password
spring.h2.console.enabled=true
spring.jpa.hibernate.ddl-auto=update
```

### Step 2: Build and Run the Application

```bash
mvn clean install
mvn spring-boot:run
```

The API will be available at `http://localhost:8080/api/tasks`.

### API Endpoints

- `GET /api/tasks`: Get all tasks
- `GET /api/tasks/{id}`: Get a task by ID
- `POST /api/tasks`: Create a new task
- `PUT /api/tasks/{id}`: Update an existing task
- `DELETE /api/tasks/{id}`: Delete a task

## Frontend Configuration

In the project folder, run the following commands:

### Step 1: Install Dependencies

```bash
npm install
```

### Step 2: Run the Application

```bash
npm start
```

The frontend will be available at `http://localhost:3000`.
