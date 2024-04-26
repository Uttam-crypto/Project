package com.library.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.library.entity.Book;
import com.library.entity.Rental;
import com.library.exception.ResourceNotFoundException;
import com.library.repository.BookRepository;
import com.library.repository.RentalRepository;




@RestController
@RequestMapping("/books")
@Validated
public class BookController {

	@Autowired
    private BookRepository bookRepository;

    @Autowired
    private RentalRepository rentalRepository;

    // Create Book
    @PostMapping
    public Book createBook(@Valid @RequestBody Book book) {
        return bookRepository.save(book);
    }

    // Retrieve Book by ID
    @GetMapping("/{id}")
    public Book getBookById(@PathVariable Long id) {
        return bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Book not found with id: " + id));
    }

    // Update Book
    @PutMapping("/{id}")
    public Book updateBook(@PathVariable Long id,@Valid @RequestBody Book bookDetails) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Book not found with id: " + id));
        book.setTitle(bookDetails.getTitle());
        book.setAuthor(bookDetails.getAuthor());
        book.setIsbn(bookDetails.getIsbn());
        book.setPublicationYear(bookDetails.getPublicationYear());
        return bookRepository.save(book);
    }

    // Delete Book
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteBook(@PathVariable Long id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Book not found with id: " + id));
        bookRepository.delete(book);
        return ResponseEntity.ok().build();
    }
    
 // Retrieve Books by Author
    @GetMapping("/author/{authorId}")
    public List<Book> getBooksByAuthor(@PathVariable Long authorId) {
        // Implement logic to retrieve books by authorId
        return bookRepository.findByAuthorId(authorId);
    }

    @GetMapping("/available")
    public ResponseEntity<?> getAvailableBooks() {
        try {
            List<Book> availableBooks = bookRepository.findByAvailableTrue();
            return ResponseEntity.ok(availableBooks);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error retrieving available books: " + e.getMessage());
        }
    }

    // Retrieve Books Currently Rented
    @GetMapping("/rented")
    public List<Book> getRentedBooks() {
        // Implement logic to retrieve rented books
    	// Assuming rentedBooks is a list of Rental objects containing Book references

    	// Extract bookIds from rentedBooks
    	List<Rental> rentedBooksList = rentalRepository.findAll();
    	List<Long> rentedBookIds = rentedBooksList.stream()
    	                                      .map(rental -> rental.getBook().getId())
    	                                      .collect(Collectors.toList());

    	// Use bookRepository to find books by their ids
    	List<Book> rentedBooks = bookRepository.findByIdIn(rentedBookIds);
        return bookRepository.findByIdIn(rentedBookIds);
    }
    
    @GetMapping("/overdue")
    public ResponseEntity<?> checkOverdueRentals() {
        try {
            // Implement logic to check for overdue rentals
            // For example:
            LocalDate today = LocalDate.now();
            List<Rental> overdueRentals = rentalRepository.findByReturnDateBefore(today.minusDays(14));
            return ResponseEntity.ok(overdueRentals);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error checking for overdue rentals: " + e.getMessage());
        }
    }
}




