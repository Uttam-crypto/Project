package com.library.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.library.entity.Author;
import com.library.exception.ResourceNotFoundException;
import com.library.repository.AuthorRepository;

@RestController
@RequestMapping("/authors")
@Validated
public class AuthorController {

    @Autowired
    private AuthorRepository authorRepository;

    // Create Author
    @PostMapping
    public Author createAuthor(@Valid @RequestBody Author author) {
        return authorRepository.save(author);
    }

    // Retrieve Author by ID
    @GetMapping("/{id}")
    public Author getAuthorById(@PathVariable Long id) {
        return authorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Author not found with id: " + id));
    }

    // Update Author
    @PutMapping("/{id}")
    public Author updateAuthor(@PathVariable Long id,@Valid @RequestBody Author authorDetails) {
        Author author = authorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Author not found with id: " + id));
        author.setName(authorDetails.getName());
        author.setBiography(authorDetails.getBiography());
        return authorRepository.save(author);
    }

    // Delete Author
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAuthor(@PathVariable Long id) {
        Author author = authorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Author not found with id: " + id));
        authorRepository.delete(author);
        return ResponseEntity.ok().build();
    }


    // Other endpoints for CRUD operations and additional functionalities
}