# clean-architecture
Repo for clean architecture workshop implemented in Kotlin

The implementation is based on the Clean Architecture from https://blog.cleancoder.com/uncle-bob/2012/08/13/the-clean-architecture.html

The structure in this repo is as follows:

1. **domain**  
   Entities, valuetypes, aggregates, factories, repositories
2. **usecase**  
   Use cases with application logic
3. **infra**  
   Different modules for infrastructure and external dependencies. Database, external services++
4. **web**  
   Entrypoint with REST controllers, DI-setup with Spring, Main application, etc.

**Domain** is the innermost part of the architecture, hence it cannot refer to any other layer.  
**Usecase** can only refer to **Domain**.  
**Infrastructure** can refer to **Usecase** and **Domain**.  
**Web** is outer most layer and can reference all other layers.

# Prerequisites

- Maven
- JDK 17
- Editor (IntelliJ or other editor of choice)

# Compiling and running the application

To compile run: `mvn clean package`

## Running the application

### From IntelliJ

- Open `CleanArchitectureWorkshopApplication` and run its main method

### From the terminal

- Open terminal of choice where Maven is available
- Navigate to `web` and type `mvn spring-boot:run` followed by enter:

```
~\clean-architecture\web> mvn spring-boot:run
```

The application should be available on `http://localhost:5233`

# Postman collection

The `clean-architecture-postman-collection.json` is a postman collection with resources to represent the different use cases in the workshop.
