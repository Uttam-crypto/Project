package com.library.tests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.library.entity.Book;

public class BookTest {

    private Validator validator;

    @BeforeEach
    public void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    public void testValidBook() {
        Book book = new Book();
        book.setTitle("Test Book");
        book.setIsbn("978-3-16-148410-0");
        book.setPublicationYear(2022);

        assertEquals(0, validator.validate(book).size());
    }

    @Test
    public void testBookTitleNotBlank() {
        Book book = new Book();
        book.setIsbn("978-3-16-148410-0");
        book.setPublicationYear(2022);

        assertEquals(1, validator.validate(book).size());
    }

    @Test
    public void testBookIsbnNotBlank() {
        Book book = new Book();
        book.setTitle("Test Book");
        book.setPublicationYear(2022);

        assertEquals(1, validator.validate(book).size());
    }

    @Test
    public void testBookPublicationYearMinValue() {
        Book book = new Book();
        book.setTitle("Test Book");
        book.setIsbn("978-3-16-148410-0");
        book.setPublicationYear(999);

        assertEquals(1, validator.validate(book).size());
    }

    @Test
    public void testBookPublicationYearValid() {
        Book book = new Book();
        book.setTitle("Test Book");
        book.setIsbn("978-3-16-148410-0");
        book.setPublicationYear(1000);

        assertEquals(0, validator.validate(book).size());
    }
}
