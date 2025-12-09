# Horse Racing Betting Application

## Overview

A comprehensive RESTful API application built with Spring Boot for managing horse racing events, participant tracking, and real-time betting operations. The system implements enterprise-grade security with JWT-based authentication, transactional betting logic, and comprehensive audit logging. Designed with microservices architecture principles and containerization support for scalable deployment.

## Core Technologies

### Backend Framework
- **Spring Boot 3.5.7** - Modern Java framework providing:
  - Auto-configuration and dependency injection
  - Embedded Tomcat server
  - Production-ready features (health checks, metrics)
  - Hot reload with Spring DevTools
  
- **Spring Framework 6.2.12** - Core components:
  - **Spring MVC** - RESTful API implementation
  - **Spring Data JPA** - Repository abstraction and database operations
  - **Spring Security** - Authentication and authorization framework
  - **Spring AOP** - Aspect-oriented programming for cross-cutting concerns

### Security & Authentication
- **Spring Security 6.5.6** - Enterprise security framework:
  - Stateless JWT authentication
  - Password encryption with BCrypt
  - Role-based access control (RBAC)
  - CORS configuration for cross-origin requests
  - Custom authentication filters and handlers
  
- **JJWT 0.12.5** (JSON Web Token) - Token-based authentication:
  - HS256 algorithm for token signing
  - Custom claims for user identity
  - Configurable token expiration
  - Secure token validation and parsing

### Data Persistence
- **PostgreSQL 15.2** - Relational database features:
  - ACID compliance for betting transactions
  - Complex queries with JOINs for race analytics
  - Indexing strategies for performance optimization
  - Connection pooling with HikariCP
  
- **Hibernate ORM 6.6.33** - Object-relational mapping:
  - Entity relationship management
  - Automatic schema generation and migration
  - Lazy loading and caching strategies
  - Transaction management
  
- **HikariCP 6.3.3** - High-performance JDBC connection pool:
  - Optimized connection management
  - Minimal overhead and fast performance
  - Configurable pool sizing

### Build & Dependency Management
- **Maven 3.9.9** - Project lifecycle management:
  - Dependency resolution and management
  - Multi-module project support
  - Plugin ecosystem integration
  - Reproducible builds

### Containerization & DevOps
- **Docker Compose** - Multi-container orchestration:
  - PostgreSQL database service
  - Environment-specific configurations
  - Volume management for data persistence
  - Network isolation and service discovery

### Code Quality & Development
- **Lombok 1.18.42** - Boilerplate code reduction:
  - Automatic getter/setter generation
  - Constructor generation
  - Builder pattern implementation
  - Logging annotation support
  
- **Jakarta Bean Validation 3.0.2** - Input validation:
  - Annotation-based constraints
  - Custom validator implementation
  - Integration with Spring MVC

### Serialization & Data Transfer
- **Jackson 2.19.2** - JSON processing:
  - Object serialization/deserialization
  - Custom serializers for complex types
  - Data binding for DTOs
  - Support for Java 8 date/time types

### Logging & Monitoring
- **SLF4J 2.0.17** with **Logback 1.5.20**:
  - Structured logging with multiple levels
  - File and console appenders
  - Custom log patterns
  - Rolling file policies for log management

## Architecture & Design Patterns

### Layered Architecture
The application follows a clean, layered architecture pattern:

1. **Presentation Layer** - REST Controllers exposing HTTP endpoints
2. **Service Layer** - Business logic and transaction management
3. **Repository Layer** - Data access abstraction using Spring Data JPA
4. **Domain Layer** - Entity models with JPA annotations

### Security Architecture
- **Stateless Authentication** - JWT tokens stored client-side
- **Filter Chain** - Custom JWT authentication filter in Spring Security chain
- **Password Security** - BCrypt hashing with configurable strength
- **Authorization** - Role-based access control with method-level security

### Database Design
- **Normalized Schema** - Relational data model with foreign key constraints
- **Entity Relationships**:
  - User to Bet (One-to-Many)
  - Race to RaceHorse (One-to-Many)
  - Horse to RaceHorse (One-to-Many)
  - RaceHorse to Bet (One-to-Many)

### API Design
- **RESTful Principles** - Resource-based URLs, HTTP verbs, status codes
- **DTO Pattern** - Data Transfer Objects for request/response separation
- **Error Handling** - Centralized exception handling with meaningful messages
- **CORS Support** - Configurable cross-origin resource sharing

## Key Features

### User Management
- **Secure Registration** - Password hashing with BCrypt
- **JWT Authentication** - Stateless token-based login system
- **User Points System** - Virtual currency management for betting
- **Role-Based Authorization** - Admin and user role separation

### Race Management
- **Race Creation & Scheduling** - Administrative race setup with timing
- **Horse Registration** - Participant management with detailed profiles
- **Race Status Tracking** - Lifecycle management (scheduled, ongoing, completed)
- **Results Processing** - Automated winner determination and payout calculation

### Betting System
- **Real-Time Bet Placement** - Transaction-safe betting operations
- **Odds Calculation** - Dynamic odds based on betting pool
- **Payout Processing** - Automated distribution to winners
- **Bet History** - Complete audit trail of user betting activity

### Security Features
- **JWT Token Management** - Secure token generation and validation
- **Password Encryption** - Industry-standard BCrypt hashing
- **CORS Configuration** - Secure cross-origin request handling
- **Input Validation** - Jakarta Bean Validation constraints
- **SQL Injection Prevention** - Parameterized queries via JPA

### Logging & Monitoring
- **Comprehensive Audit Logging** - All critical operations logged
- **Authentication Tracking** - Login attempts and token generation
- **Error Logging** - Detailed stack traces for debugging
- **Performance Logging** - SQL query execution tracking

## Technical Highlights

### Performance Optimizations
- **Connection Pooling** - HikariCP for efficient database connections
- **Lazy Loading** - JPA lazy fetching strategies to minimize queries
- **Query Optimization** - Indexed columns and efficient JOIN operations
- **Caching Strategies** - Hibernate second-level cache support

### Transaction Management
- **ACID Compliance** - PostgreSQL transaction guarantees
- **Declarative Transactions** - Spring @Transactional annotations
- **Rollback Handling** - Automatic rollback on exceptions
- **Isolation Levels** - Configurable transaction isolation

### Code Quality
- **Clean Code Principles** - SOLID design patterns
- **Dependency Injection** - Spring IoC container
- **Separation of Concerns** - Layered architecture
- **DTO Pattern** - Clear API contracts

### DevOps Ready
- **Containerization** - Docker Compose configuration
- **Environment Configuration** - Externalized properties
- **Health Checks** - Spring Boot Actuator endpoints
- **Graceful Shutdown** - Proper resource cleanup

## Project Structure

```
src/main/java/horses/
├── MendelApplication.java              # Main Spring Boot application entry point
│
├── Controllers/                         # REST API Controllers
│   ├── RegisterController.java         # Authentication endpoints (register/login)
│   └── CreateGame.java                 # Game management endpoints
│
├── Services/                            # Business Logic Layer
│   └── RegisterService.java            # User management & authentication service
│
├── Repositories/                        # Data Access Layer
│   └── RegisterInterface.java          # Spring Data JPA repository for User entity
│
├── databases/                           # JPA Entity Models
│   ├── User.java                       # User entity with validation
│   └── Game.java                       # Game/Race entity
│
├── Dtos/                                # Data Transfer Objects
│   ├── RegisterDto.java                # User registration request
│   ├── LoginResponse.java              # JWT authentication response
│   ├── BetRequest.java                 # Bet placement request
│   └── CreateRaceRequest.java          # Race creation request
│
└── configurations/                      # Security & Application Configuration
    ├── SecurityConfig.java             # Spring Security configuration
    ├── JwtUtil.java                    # JWT token utilities
    ├── JwtAuthenticationFilter.java    # Custom JWT authentication filter
    ├── LoggedUserLog.java              # Authentication event logging
    └── CorsConfig.java                 # Cross-Origin Resource Sharing config

src/main/resources/
├── application.properties              # Application configuration
└── schema.sql                          # Database schema (optional)

docker-compose.yml                      # PostgreSQL containerization
pom.xml                                 # Maven dependencies & plugins
```

## API Endpoints

### Authentication
- `POST /api/register` - User registration with validation
- `POST /api/login` - JWT token generation
- `GET /api/user` - Get authenticated user details (protected)

### Race Management
- `POST /api/races` - Create new race (admin)
- `GET /api/races` - List all races
- `GET /api/races/{id}` - Get race details
- `PUT /api/races/{id}/start` - Start a race (admin)
- `PUT /api/races/{id}/finish` - Complete race and process payouts (admin)

### Betting
- `POST /api/bets` - Place a bet on a race
- `GET /api/bets/user/{userId}` - Get user's betting history
- `GET /api/bets/race/{raceId}` - Get all bets for a race

### Horse Management
- `POST /api/horses` - Register new horse (admin)
- `GET /api/horses` - List all horses
- `GET /api/horses/{id}` - Get horse details

## Technical Skills Demonstrated

### Backend Development
- RESTful API design and implementation
- MVC architecture pattern
- Service-oriented architecture
- Repository pattern with Spring Data JPA
- DTO pattern for data transfer

### Database Management
- Relational database design and normalization
- JPA entity relationships (One-to-Many, Many-to-One)
- Complex SQL queries and joins
- Transaction management and ACID properties
- Database indexing and optimization

### Security Implementation
- JWT-based stateless authentication
- Password encryption with BCrypt
- Custom security filters
- CORS configuration
- Role-based access control
- Input validation and sanitization

### Spring Framework Expertise
- Dependency injection and IoC
- Spring Boot auto-configuration
- Spring Security framework
- Spring Data JPA
- Spring MVC
- Aspect-oriented programming

### DevOps & Deployment
- Docker containerization
- Docker Compose orchestration
- Environment configuration management
- Application logging and monitoring
- Maven build automation

### Software Engineering Practices
- Clean code principles
- SOLID design patterns
- Separation of concerns
- Error handling and exception management
- Code documentation
- Version control with Git

## Database Schema

### Users Table
- `id` (Primary Key)
- `username` (Unique, Not Null)
- `password` (Encrypted, Not Null)
- `authorities` (Role information)
- `points` (Virtual currency for betting)

### Horses Table
- `id` (Primary Key)
- `name` (Not Null)
- `breed`
- `age`
- `statistics` (Performance metrics)

### Races Table
- `id` (Primary Key)
- `name` (Not Null)
- `date` (Scheduled date/time)
- `status` (SCHEDULED, ONGOING, COMPLETED)
- `track_conditions`

### RaceHorses Table (Join Table)
- `id` (Primary Key)
- `race_id` (Foreign Key)
- `horse_id` (Foreign Key)
- `starting_position`
- `finish_position`
- `odds`

### Bets Table
- `id` (Primary Key)
- `user_id` (Foreign Key)
- `race_horse_id` (Foreign Key)
- `amount` (Bet amount)
- `potential_payout`
- `status` (PENDING, WON, LOST)
- `timestamp`
eature documentation.
## Configuration Details

### Application Properties
The application uses externalized configuration for environment-specific settings:

- **Database Connection** - PostgreSQL JDBC configuration
- **JPA/Hibernate** - Auto DDL, SQL logging, dialect configuration
- **Logging** - Custom patterns, file output, debug levels
- **JWT** - Secret key (256-bit), token expiration (24 hours)
- **CORS** - Allowed origins, methods, headers

### Docker Compose
Containerized PostgreSQL database with:
- Version-pinned image (postgres:15.2)
- Environment variables for credentials
- Automatic port mapping
- Volume mounting for data persistence

## Development Practices

- **Version Control** - Git for source code management
- **Code Quality** - Lombok for clean code
- **Testing Ready** - JUnit and Mockito integration
- **Documentation** - Comprehensive inline comments
- **Logging Strategy** - SLF4J with structured logging
- **Error Handling** - Global exception handlers
- **Input Validation** - Jakarta Bean Validation annotations

## Future Enhancements

- WebSocket integration for real-time race updates
- Admin dashboard with Spring Boot Admin
- Metrics and monitoring with Spring Boot Actuator
- Redis caching for frequently accessed data
- Message queue integration (RabbitMQ/Kafka)
- Microservices architecture migration
- Kubernetes deployment configuration
- CI/CD pipeline with Jenkins/GitHub Actions
