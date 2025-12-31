# Assignment 3: Library Management System - Structural Design Patterns

Spring Boot application demonstrating Facade and Decorator design patterns with H2 database.

## Patterns Implemented

### 1. Decorator Pattern
Dynamic book pricing with stackable decorators:
- **FeaturedBookDecorator** - Adds 10% to book price
- **BestsellerBookDecorator** - Adds 5% to book price
- Both decorators can be applied together (+15.5% total)

### 2. Facade Pattern
**LibraryFacade** simplifies complex operations:
- `addNewBook()` - Creates book and stores in repository
- `getFeaturedBooks()` - Retrieves books with Featured decorator applied
- `getBestsellers()` - Retrieves books with Bestseller decorator applied
- `getAllBooksWithDecorators()` - Retrieves all books with appropriate decorators

## Requirements

- Java 17 (OpenJDK)
- Maven 3.6+

## Build & Run

### Build
```bash
.\build.bat
```
Expected output: `BUILD SUCCESS` with 6 tests passing.

### Run
```bash
.\run.bat
```
Application starts on `http://localhost:8080`

## Testing

### API Endpoints

**Create Book (Featured + Bestseller)**
```bash
curl -X POST http://localhost:8080/api/books -H "Content-Type: application/json" -d "{\"title\":\"New Book\",\"author\":\"Alex\",\"category\":\"Action\",\"isbn\":\"string\",\"price\":100,\"featured\":true,\"bestseller\":true}"
```

Expected response:
```json
{
  "id": 11,
  "title": "New Book",
  "author": "Alex",
  "category": "Action",
  "isbn": "string",
  "originalPrice": 100,
  "displayPrice": 115.5,
  "description": "[BESTSELLER] [FEATURED] New Book by Alex",
  "featured": true,
  "bestseller": true
}
```

**Get All Books (with Decorators Applied)**
```bash
curl http://localhost:8080/api/books
```

Expected: Array of books with `displayPrice` calculated based on decorators.

**Get Featured Books (+10% price)**
```bash
curl http://localhost:8080/api/books/featured
```

Expected: Books where `featured=true`, with `displayPrice = originalPrice * 1.10`

**Get Bestsellers (+5% price)**
```bash
curl http://localhost:8080/api/books/bestsellers
```

Expected: Books where `bestseller=true`, with `displayPrice = originalPrice * 1.05`

**Get Book by ID**
```bash
curl http://localhost:8080/api/books/1
```

Expected response (Book #1 has both decorators):
```json
{
  "id": 1,
  "title": "Clean Code",
  "author": "Robert C. Martin",
  "category": "Programming",
  "isbn": "978-0132350884",
  "originalPrice": 45.99,
  "displayPrice": 53.36,
  "description": "[BESTSELLER] [FEATURED] Clean Code by Robert C. Martin",
  "featured": true,
  "bestseller": true
}
```

**Delete Book**
```bash
curl -X DELETE http://localhost:8080/api/books/11
```

Expected: HTTP 204 No Content

### Swagger UI

Access: http://localhost:8080/swagger-ui.html

Interactive API documentation with all endpoints available for testing.

### H2 Database Console

Access: http://localhost:8080/h2-console

**Connection Settings:**
- JDBC URL: `jdbc:h2:mem:librarydb`
- Username: `sa`
- Password: `password`

**Query Books:**
```sql
SELECT * FROM BOOKS;
```

Expected: Table showing 10 books with columns: ID, TITLE, AUTHOR, CATEGORY, ISBN, PRICE, FEATURED, BESTSELLER

### Unit Tests
```bash
.\run.bat test
```

Expected output: `Tests run: 6, Failures: 0, Errors: 0, Skipped: 0`

**Tests Included:**
- `testSimpleBook()` - Basic book without decorators
- `testFeaturedBookDecorator()` - Featured book price (+10%)
- `testBestsellerBookDecorator()` - Bestseller book price (+5%)
- `testCombinedDecorators()` - Both decorators (+15.5%)
- `testDecoratorOrder()` - Verify decorator order doesn't affect final price

## How It Works

When a book is retrieved, the Facade and Decorator patterns work together:

1. **Controller** receives HTTP GET request
2. **Facade Pattern** (`LibraryFacade`) coordinates the operation
3. **Service Layer** retrieves book from repository
4. **Decorator Pattern** applies pricing modifications:
   - If `featured=true`: Wraps in `FeaturedBookDecorator` (+10%)
   - If `bestseller=true`: Wraps in `BestsellerBookDecorator` (+5%)
   - Both: Stacks decorators (+15.5% total)
5. **Response** returned with `originalPrice` and calculated `displayPrice`

## API Reference

| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/api/books` | Create a new book |
| GET | `/api/books` | Get all books with decorators |
| GET | `/api/books/{id}` | Get book by ID with decorators |
| GET | `/api/books/featured` | Get all featured books |
| GET | `/api/books/bestsellers` | Get all bestseller books |
| PUT | `/api/books/{id}` | Update a book |
| DELETE | `/api/books/{id}` | Delete a book |

## Decorator Pricing Examples

| Book Type | Original Price | Featured | Bestseller | Display Price | Calculation |
|-----------|---------------|----------|------------|---------------|-------------|
| Regular | $100.00 | No | No | $100.00 | No change |
| Featured | $100.00 | Yes | No | $110.00 | $100 × 1.10 |
| Bestseller | $100.00 | No | Yes | $105.00 | $100 × 1.05 |
| Both | $100.00 | Yes | Yes | $115.50 | $100 × 1.10 × 1.05 |

## Sample Data

10 books pre-loaded on startup:
- Programming: Clean Code, Design Patterns, The Pragmatic Programmer, Effective Java, Head First Design Patterns, Spring Boot in Action
- Fiction: 1984, To Kill a Mockingbird, The Great Gatsby
- History: Sapiens
