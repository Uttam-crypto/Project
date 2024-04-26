
package com.library.controller;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.library.entity.Book;
import com.library.entity.Rental;
import com.library.exception.IllegalBookStateException;
import com.library.exception.ResourceNotFoundException;
import com.library.repository.BookRepository;
import com.library.repository.RentalRepository;
@RestController
@RequestMapping("/rentals")
@Validated
public class RentalController {

    @Autowired
    private RentalRepository rentalRepository;

    @Autowired
    private BookRepository bookRepository;

 // Create Rental
    @PostMapping
    public ResponseEntity<?> createRental(@Valid @RequestBody Rental rental) {
        try {
            Book book = rental.getBook(); // Assuming Rental has a method to get the associated Book
            if (book == null) {
                throw new ResourceNotFoundException("Book is not provided in the rental information");
            }

            if (!book.isAvailable()) {
                throw new IllegalBookStateException("Book with id " + book.getId() + " is already rented");
            }

            book.setAvailable(false);
            bookRepository.save(book);

            Rental savedRental = rentalRepository.save(rental);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedRental);
        } catch (ResourceNotFoundException | IllegalBookStateException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }


    // Retrieve Rental by ID
    @GetMapping("/{id}")
    public ResponseEntity<?> getRentalById(@PathVariable Long id) {
        try {
            Rental rental = rentalRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("Rental not found with id: " + id));
            return ResponseEntity.ok(rental);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    // Update Rental (Return a book)
    @PutMapping("/return/{id}")
    public ResponseEntity<?> returnBook(@PathVariable Long id) {
        try {
            Rental rental = rentalRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("Rental not found with id: " + id));

            Book book = rental.getBook(); // Assuming Rental has a method to get the associated Book
            if (book == null) {
                throw new ResourceNotFoundException("Book not found for rental with id: " + id);
            }

            book.setAvailable(true);
            bookRepository.save(book);

            return ResponseEntity.ok("Book with id " + book.getId() + " has been returned.");
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}
