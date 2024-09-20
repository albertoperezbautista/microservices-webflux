# Spring Boot Microservices Project

This repository features a project developed using Spring Boot with a microservices architecture for managing clients, people, accounts, and financial transactions.

## Project Overview

The system is composed of two independent microservices, each responsible for a specific domain:

1. **Client and People Microservice:**

   - Complete CRUD operations for managing clients and people.
   - RESTful APIs for creating, reading, updating, and deleting records.
   - Reactive API implementation using **Spring WebFlux** for non-blocking operations.

2. **Accounts and Transactions Microservice:**
   - Full CRUD for bank accounts and associated transactions.
   - Business logic to handle transactions between accounts.
   - Reactive handling of financial transactions using **WebFlux** to support high concurrency.

## Key Features

- **Spring Boot**: Framework for building Java-based microservices applications.
- **Microservices**: Distributed architecture that allows scalability and independent deployment of each service.
- **Spring WebFlux**: Non-blocking, reactive programming model for building reactive APIs and ensuring system responsiveness under high load.
- **PostgreSQL**: Relational database used to manage data efficiently.
- **Docker**: Containerization of the database to facilitate configuration and deployment across different environments.
- **Unit Testing**: Automated testing of each module using **JUnit** and **Mockito**.
- **Integration Testing**: Ensures the microservices interact correctly with each other and the database.

## Technologies Used

- **Java 17**
- **Spring Boot 3.3**
- **Spring Data JPA**
- **Spring WebFlux**
- **PostgreSQL**
- **Docker**
- **JUnit 5**
- **Mockito**
- **Maven**

## Installation and Setup

### Prerequisites

- **Java 17** or higher.
- **Maven** installed.
- **Docker** installed for the database.

### Step-by-step

1. Clone the repository:

   ```bash
   git clone https://github.com/username/springboot-microservices.git
   ```

2. Navigate to the project directory:

   ```bash
   cd springboot-microservices
   ```

3. Build the project using Maven:

   ```bash
   mvn clean install
   ```

4. Start the PostgreSQL database with Docker:

   ```bash
   docker-compose up -d
   ```

5. Run the services with WebFlux:
   - You can start each microservice by running the Spring Boot application with the command:
   ```bash
   mvn spring-boot:run
   ```
   - The **WebFlux** APIs for each microservice will be available at the appropriate endpoints.
