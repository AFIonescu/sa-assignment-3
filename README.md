# Structural Design Patterns - Book Management System

## Patterns Implemented

### Facade Pattern
`LibraryFacade` - simplified interface for library operations:
- `addBook(Book)` - adds book through service layer
- `getFeaturedBooks()` - returns books with Featured decorator applied

### Decorator Pattern
`BookDecorator` interface with implementations:
- `BasicBook` - wraps Book entity
- `FeaturedBookDecorator` - adds "[FEATURED]" to description
- `BestsellerBookDecorator` - adds "[BESTSELLER]" to description

## Project Structure

```
src/main/java/org/example/structural/
├── controller/
│   └── LibraryController.java
├── dto/
│   └── BookDto.java
├── entity/
│   └── Book.java
├── repository/
│   └── BookRepository.java
├── service/
│   ├── BookService.java
│   ├── LibraryFacade.java
│   ├── BookDecorator.java
│   ├── BasicBook.java
│   ├── FeaturedBookDecorator.java
│   └── BestsellerBookDecorator.java
└── utils/
    └── BookMapper.java
```

## Requirements

- Java 17 (Amazon Corretto)
- Maven

## Run

```bash
mvn spring-boot:run
```

## Access

- Swagger UI: http://localhost:8080/swagger-ui/index.html
- H2 Console: http://localhost:8080/h2-console
  - JDBC URL: `jdbc:h2:mem:librarydb`
  - Username: `sa`
  - Password: `password`

## API Endpoints

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/api/books` | Get all books |
| GET | `/api/books/{id}` | Get book by ID |
| POST | `/api/books` | Add new book |
| PUT | `/api/books/{id}` | Update book |
| DELETE | `/api/books/{id}` | Delete book |
