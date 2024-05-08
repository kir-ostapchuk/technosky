# Account-Service

Account-Service is a RESTful API service designed to facilitate money transfers between users. It stores user data, including name, unique document number, and document type (passport, driver's license), as well as user account information, including the account balance with two decimal places precision (e.g., 2.31) and the account currency. Additionally, it keeps track of all account-related operations.

## Technologies Used
- Java 21
- Spring Boot 3.2.5
- Gradle
- PostgreSQL

## Prerequisites
- Java 21
- Docker installed
- Docker Compose installed

## Setup and Usage
1. Clone the repository: git clone https://github.com/your-account-service-repo.git
2. Navigate to the project directory: cd account-service
3. Create a `.env` file in the project root directory and configure the following environment variables:
   POSTGRES_DB=your_database_name
   POSTGRES_USER=your_username
   POSTGRES_PASSWORD=your_password
   POSTGRES_PORT=5432
   POSTGRES_HOSTNAME=postgres
   SPRING_PROFILES_ACTIVE=dev
4. Build and run the application using Docker Compose: docker-compose up
5. The Account-Service API will be accessible at `http://localhost:8080`.

## Testing
The project includes both unit tests and integration tests to ensure the correctness of the implemented functionality. The following testing frameworks are used:

- JUnit Jupiter: for writing and executing unit tests
- Mockito: for creating mock objects in unit tests
- SpringBootTest: for integration testing

To run the tests, execute the following command: ./gradlew test

## Database Migrations
The project includes Liquibase migration scripts to manage the database schema. The migration scripts can be found in the `src/main/resources/db/migration` directory. Liquibase will automatically apply these scripts on application startup. Make sure the database configuration in the `.env` file matches your PostgreSQL setup.

## Integrated OpenAPI Documentation
The Account-Service API includes integrated OpenAPI documentation. Once the application is running, you can access the OpenAPI documentation at `http://localhost:8080/swagger-ui.html`. This documentation provides detailed information about the available endpoints, request/response structures, and allows for easy testing and exploration of the API.

Please note that the OpenAPI documentation is only available in the `dev` profile. Make sure the `SPRING_PROFILES_ACTIVE` environment variable is set to `dev` in the `.env` file to enable the OpenAPI documentation.