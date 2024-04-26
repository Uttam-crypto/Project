package com.library.tests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.library.entity.Author;

public class AuthorTest {
    
    private Validator validator;

    @BeforeEach
    public void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    public void testValidAuthor() {
        Author author = new Author();
        author.setName("John Doe");
        author.setBiography("Lorem ipsum");
        
        assertEquals(0, validator.validate(author).size());
    }

    @Test
    public void testAuthorNameNotBlank() {
        Author author = new Author();
        author.setBiography("Lorem ipsum");

        assertEquals(1, validator.validate(author).size());
    }

    @Test
    public void testAuthorBiographyNotBlank() {
        Author author = new Author();
        author.setName("John Doe");

        assertEquals(1, validator.validate(author).size());
    }
    
    @Test
    public void testAuthorNameAndBiographyNotBlank() {
        Author author = new Author();

        assertEquals(2, validator.validate(author).size());
    }
}

