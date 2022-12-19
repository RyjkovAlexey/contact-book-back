package ru.alexey.contactbook.contactbookback.exceptions;

import org.springframework.http.HttpStatus;

public abstract class BaseUserException extends RuntimeException{
    public BaseUserException(String message) {
        super(message);
    }

    public abstract HttpStatus httpStatus();
}
