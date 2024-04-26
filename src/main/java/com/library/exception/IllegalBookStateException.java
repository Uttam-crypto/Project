package com.library.exception;

public class IllegalBookStateException extends RuntimeException {
    public IllegalBookStateException(String message) {
        super(message);
    }
}

