
# Spring Thymeleaf Template Application

This is a Spring Boot web application template built with Thymeleaf, designed to manage student information. It includes CRUD operations for student records with fields such as name, email, address, and birthday. The application integrates Thymeleaf for server-side templating, H2 for in-memory database support, and includes Bootstrap for UI styling.

## Project Structure

- **`src/main/java`**: Contains all Java source code.
  - **`controller`**: Defines endpoints and request handling for the Student entity.
  - **`dto`**: Holds Data Transfer Objects used to transport data between layers.
  - **`entity`**: Contains entity classes mapping to database tables.
  - **`repository`**: Interfaces extending Spring Data JPA for CRUD operations.
  - **`service`**: Business logic layer managing Student entities.
- **`src/main/resources/templates`**: Thymeleaf templates for the application UI.
- **`pom.xml`**: Maven configuration for dependencies and project setup.

## Features

- **CRUD Operations**: Create, Read, Update, and Delete student records.
- **Form Validation**: Utilizes `@Valid` for server-side validation.
- **In-Memory Database (H2)**: Easily stores and retrieves data during runtime.
- **Thymeleaf Templating**: Renders dynamic HTML pages.
- **Bootstrap Styling**: Responsive and modern UI design.

## Technologies

- **Java 11** and **Spring Boot**
- **Thymeleaf** for server-side rendering
- **H2 Database** for in-memory storage
- **Bootstrap** for styling
- **Maven** for dependency management

## Setup

1. **Clone the Repository**
   ```bash
   git clone <repository-url>
   cd springThymeleafTemplateApplication
   ```

2. **Build the Project**
   ```bash
   ./mvnw clean install
   ```

3. **Run the Application**
   ```bash
   ./mvnw spring-boot:run
   ```

4. **Access the Application**
   - Open a web browser and navigate to [http://localhost:8080/students](http://localhost:8080/students) to view the student repository.

## Endpoints

### Student Management

- **View All Students**: `GET /students`
- **Add New Student**: `GET /students/new` (Form), `POST /students/new` (Submit)
- **Edit Student**: `GET /students/{id}`, `POST /students/{id}`
- **Delete Student**: `GET /students/{id}/delete`

## Database Configuration

- By default, the application uses an H2 in-memory database. Access the H2 console at [http://localhost:8080/h2-console](http://localhost:8080/h2-console).
- Configure database connection in `application.properties` if you want to persist data outside runtime.

```properties
# H2 Database
spring.datasource.url=jdbc:h2:mem:testdb
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=password
spring.h2.console.enabled=true
spring.jpa.hibernate.ddl-auto=update
```

## Development Notes

- **Model Binding**: Uses `@ModelAttribute` in controllers for form handling with DTOs.
- **Validation**: Validation annotations (e.g., `@NotNull`, `@Size`) are added to DTOs for server-side validation.
- **Mapper**: Contains a `StudentMapper` class to map between entities and DTOs.

## Dependencies

Key dependencies include:
- `Spring Boot Starter Web`
- `Spring Boot Starter Thymeleaf`
- `Spring Boot Starter Data JPA`
- `H2 Database`
- `Bootstrap` (for UI styling)

These dependencies are managed via `pom.xml`.

## Future Enhancements

- Add pagination for the student list view.
- Extend validation rules for better data integrity.
- Integrate Swagger for API documentation.
- Switch to a persistent database like MySQL or PostgreSQL for production use.
