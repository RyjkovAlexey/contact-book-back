package ru.alexey.contactbook.contactbookback.exception;

import org.springframework.http.HttpStatus;

public abstract class BaseAccountException extends RuntimeException{
    public BaseAccountException(String message) {
        super(message);
    }

    public abstract HttpStatus httpStatus();
}
