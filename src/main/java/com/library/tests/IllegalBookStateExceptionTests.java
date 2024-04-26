package com.library.tests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.library.exception.IllegalBookStateException;

public class IllegalBookStateExceptionTests {

    @Test
    public void testIllegalBookStateException() {
        String errorMessage = "Test error message";
        IllegalBookStateException exception = new IllegalBookStateException(errorMessage);
        assertEquals(errorMessage, exception.getMessage());
    }
}
