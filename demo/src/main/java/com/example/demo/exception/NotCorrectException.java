package com.example.demo.exception;

public class NotCorrectException extends RuntimeException{
    public NotCorrectException() {
        super();
    }

    public NotCorrectException(String message) {
        super(message);
    }

    public NotCorrectException(String message, Throwable cause) {
        super(message, cause);
    }

    public NotCorrectException(Throwable cause) {
        super(cause);
    }
}
