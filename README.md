# Book Rental System

This project implements a Book Rental System using Spring Boot.

## Setup Instructions

1. Clone the repository:
   ```bash
   https://github.com/Uttam-crypto/SampleProject.git

2. Navigate to the project directory:
   cd book-rental-system

3. Configure the database connection:
   Open src/main/resources/application.properties.
   Update the database connection settings as per your environment.

4. Build the project:
   ./mvnw clean package

5. Run The Application :
   ./mvnw spring-boot:run


**ENDPOINTS**

Books

GET /books: Retrieve all books.

GET /books/{id}: Retrieve a specific book by ID.

POST /books: Create a new book.

PUT /books/{id}: Update an existing book.

DELETE /books/{id}: Delete a book by ID.

Authors

GET /authors: Retrieve all authors.

GET /authors/{id}: Retrieve a specific author by ID.

POST /authors: Create a new author.

PUT /authors/{id}: Update an existing author.

DELETE /authors/{id}: Delete an author by ID.

Rentals

GET /rentals: Retrieve all rentals.

GET /rentals/{id}: Retrieve a specific rental by ID.

POST /rentals: Create a new rental.

PUT /rentals/{id}: Update an existing rental.

DELETE /rentals/{id}: Delete a rental by ID.

Additional Endpoints

GET /books/author/{authorId}: Retrieve books by author ID.

GET /books/available: Retrieve books available for rent.

GET /books/rented: Retrieve books currently rented.


**EXAMPLE**

CreateBook

POST /books

Content-Type: application/json

{
  "title": "Sample Book",
  
  "authorId": 1,
  
  "isbn": "978-3-16-148410-0",
  
  "publicationYear": 2024
}


RentBook

POST /api/rentals

Content-Type: application/json

{
  "bookId": 1,
  
  "renterName": "John Doe",
  
  "rentalDate": "2024-04-26",
  
  "returnDate": "2024-05-10"
}




   
