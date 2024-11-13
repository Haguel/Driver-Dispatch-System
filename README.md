### Project README

# Driver Dispatch System Project

## Description

This project is a web application built using Java, Spring Boot, and Thymeleaf. It provides a platform for managing vehicles, drivers, and cargo orders. The application includes user roles such as admin and dispatcher, each with specific access rights.

## Interesting Techniques

- **Spring Security Configuration**: The project uses Spring Security to manage authentication and authorization. The configuration is done programmatically in the `WebSecurityConfig` class. [Spring Security Documentation](https://docs.spring.io/spring-security/site/docs/current/reference/html5/)
- **Thymeleaf Templates**: Thymeleaf is used for rendering dynamic web pages. The `mainMenu.html` template demonstrates how to use Thymeleaf fragments and conditional rendering based on user roles. [Thymeleaf Documentation](https://www.thymeleaf.org/doc/tutorials/3.0/usingthymeleaf.html)
- **Model Attribute Binding**: The `MenuController` class shows how to bind model attributes to endpoints, making them accessible in Thymeleaf templates.

## Project Structure

```plaintext
src/
├── main/
│   ├── java/
│   │   └── dev/
│   │       └── haguel/
│   │           ├── config/
│   │           ├── controller/
│   │           ├── dao/
│   │           ├── DTO/
│   │           ├── enumeration/
│   │           ├── exception/
│   │           ├── factory/
│   │           ├── model/
│   │           ├── serializer/
│   │           ├── service/
|   |           ├── simulator/
│   │           └── util/
│   └── resources/
│       ├── static/
│       └── templates/
│           └── fragments/
└── test/
```

- **config/**: Contains configuration classes.
- **controller/**: Contains Spring MVC controllers.
- **dao/**: Contains data access objects (repositories).
- **DTO/**: Contains data transfer objects.
- **enumeration/**: Contains enum classes.
- **exception/**: Contains exception classes.
- **factory/**: Contains factory classes that generates data for simulation.
- **model/**: Contains entity classes.
- **serializer/**: Contains serializer classes for valid json display.
- **service/**: Contains service classes.
- **simulator/**: Contains simulator class `CargoDelivarySimulator` which simulates real-time cargo delivery process.
- **util/**: Contains utility classes.
- **templates/**: Contains Thymeleaf templates for rendering web pages.
- **fragments/**: Contains Thymeleaf fragments used in templates.

## External Libraries

- [Spring Boot](https://spring.io/projects/spring-boot)
- [Spring Security](https://spring.io/projects/spring-security)
- [Spring Data JPA](https://spring.io/projects/spring-data-jpa)
- [BCrypt](https://docs.spring.io/spring-security/site/docs/current/api/org/springframework/security/crypto/bcrypt/BCryptPasswordEncoder.html)
- [Thymeleaf](https://www.thymeleaf.org/)
- [Lombok](https://projectlombok.org/)
- [JUnit](https://junit.org/junit5/)
- [Flyway](https://flywaydb.org/)
- [PostgreSQL](https://www.postgresql.org/)

---