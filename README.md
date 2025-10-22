Summary
A concise, production-minded Spring Boot backend created as a portfolio project. Implements secure user registration and authentication, lightweight in-memory game session logic for demonstrations, and persistent user storage in a SQL database hosted in Docker.

What this project demonstrates
- Secure authentication using Spring Security.
- JPA-based user modeling and repository-driven persistence.
- Clear service/controller separation and small, maintainable code surface.
- Use of Docker to host the SQL database.

Key technologies
- Java, Spring Boot
- Spring Security, Spring Data JPA
- PostgreSQL
- Maven, Lombok

Architecture highlights and important files
- `src/main/java/horses/configurations/SecurityConfig` — authentication and security rules
- `src/main/java/horses/Services/RegisterService` — registration logic
- `src/main/java/horses/Controllers` — controllers for game and user flows
- `src/main/java/horses/databases/Game` — record representing a game session
