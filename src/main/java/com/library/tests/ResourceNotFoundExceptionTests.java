package com.library.tests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.library.exception.ResourceNotFoundException;

public class ResourceNotFoundExceptionTests {

    @Test
    public void testResourceNotFoundException() {
        String errorMessage = "Test error message";
        ResourceNotFoundException exception = new ResourceNotFoundException(errorMessage);
        assertEquals(errorMessage, exception.getMessage());
    }
}
