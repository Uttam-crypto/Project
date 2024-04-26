package com.library.tests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.library.entity.Rental;

public class RentalTest {

    private Validator validator;

    @BeforeEach
    public void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    public void testValidRental() {
        Rental rental = new Rental();
        rental.setRentalDate(LocalDate.now());
        rental.setReturnDate(LocalDate.now().plusDays(7)); // Set return date to 7 days from rental date
        rental.setRenterName("John Doe");

        assertEquals(0, validator.validate(rental).size());
    }

    @Test
    public void testRentalDateNotNull() {
        Rental rental = new Rental();
        rental.setReturnDate(LocalDate.now().plusDays(7)); // Set return date to 7 days from rental date
        rental.setRenterName("John Doe");

        assertEquals(1, validator.validate(rental).size());
    }

    @Test
    public void testReturnDateFutureOrPresent() {
        Rental rental = new Rental();
        rental.setRentalDate(LocalDate.now());
        rental.setReturnDate(LocalDate.now().minusDays(1)); // Set return date to yesterday
        rental.setRenterName("John Doe");

        assertEquals(1, validator.validate(rental).size());
    }

    @Test
    public void testBookNotNull() {
        Rental rental = new Rental();
        rental.setRentalDate(LocalDate.now());
        rental.setReturnDate(LocalDate.now().plusDays(7)); // Set return date to 7 days from rental date
        rental.setRenterName("John Doe");

        assertEquals(1, validator.validate(rental).size());
    }

    @Test
    public void testRenterNameNotBlank() {
        Rental rental = new Rental();
        rental.setRentalDate(LocalDate.now());
        rental.setReturnDate(LocalDate.now().plusDays(7)); // Set return date to 7 days from rental date

        assertEquals(1, validator.validate(rental).size());
    }
}
