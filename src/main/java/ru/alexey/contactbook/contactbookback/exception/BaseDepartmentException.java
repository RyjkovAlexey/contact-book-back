package ru.alexey.contactbook.contactbookback.exception;

import org.springframework.http.HttpStatus;

public abstract class BaseDepartmentException extends RuntimeException{
    public BaseDepartmentException(String message) {
        super(message);
    }

    public abstract HttpStatus httpStatus();
}
