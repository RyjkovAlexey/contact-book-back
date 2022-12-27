package ru.alexey.contactbook.contactbookback.exception;

import org.springframework.http.HttpStatus;

public abstract class BaseContactException extends RuntimeException {
    public BaseContactException(String message) {
        super(message);
    }

    public abstract HttpStatus httpStatus();
}
