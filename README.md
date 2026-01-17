# Structural Design Patterns - Book Management System

## Patterns Implemented

### Facade Pattern
`LibraryFacade` - simplified interface for library operations interacting with multiple services and repositories:
- `addBook(Book)` - adds book through service layer and returns saved book
- `findBooksByCategory(String)` - finds books by category
- `getFeaturedBooks()` - returns books with Featured decorator applied
- `getBestsellerBooks()` - returns books with Bestseller decorator applied
- `getAllBooks()` - retrieves all books from repository
- `getBookById(Long)` - retrieves book by ID
- `updateBook(Long, Book)` - updates existing book
- `deleteBook(Long)` - deletes book by ID

### Decorator Pattern
`BookDecorator` interface with implementations that dynamically add features to books:
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

- Swagger UI: http://localhost:8080/swagger
- H2 Console: http://localhost:8080/h2-console
  - JDBC URL: `jdbc:h2:mem:librarydb`
  - Username: `sa`
  - Password: `password`

## API Endpoints

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/api/books` | Get all books |
| GET | `/api/books/{id}` | Get book by ID |
| GET | `/api/books/category/{category}` | Get books by category (uses LibraryFacade) |
| GET | `/api/books/featured` | Get all books with Featured decorator |
| GET | `/api/books/bestsellers` | Get all books with Bestseller decorator |
| POST | `/api/books` | Add new book |
| PUT | `/api/books/{id}` | Update book |
| DELETE | `/api/books/{id}` | Delete book |
