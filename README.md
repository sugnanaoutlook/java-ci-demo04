# Hello World Spring Boot Application

A simple Spring Boot Maven application with a welcome controller demonstrating basic REST endpoints.

## Features

- Spring Boot 3.2.1
- Maven build system
- REST API endpoints
- Custom application banner
- Health check endpoint

## Prerequisites

- Java 17 or higher
- Maven 3.6 or higher

## Running the Application

1. Clone the repository
2. Navigate to the project directory
3. Run the application using Maven:

```bash
mvn spring-boot:run
```

Or build and run the JAR:

```bash
mvn clean package
java -jar target/hello-world-app-1.0.0.jar
```

## API Endpoints

The application provides the following endpoints:

- `GET /` - Returns a simple "Hello World" message
- `GET /welcome` - Returns a welcome message
- `GET /welcome/{name}` - Returns a personalized welcome message
- `GET /greet?name={name}` - Returns a greeting with optional name parameter
- `GET /health` - Health check endpoint

## Examples

```bash
# Basic hello world
curl http://localhost:8081/

# Welcome message
curl http://localhost:8081/welcome

# Personalized welcome
curl http://localhost:8081/welcome/John

# Greeting with parameter
curl http://localhost:8081/greet?name=Alice

# Health check
curl http://localhost:8081/health
```

## Project Structure

```
src/
├── main/
│   ├── java/
│   │   └── com/example/helloworldapp/
│   │       ├── HelloWorldApplication.java
│   │       └── controller/
│   │           └── WelcomeController.java
│   └── resources/
│       ├── application.properties
│       └── banner.txt
└── test/
    └── java/
```
