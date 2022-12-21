package ru.alexey.contactbook.contactbookback.exceptions;

import org.springframework.http.HttpStatus;

public abstract class BaseLabelException extends RuntimeException {
    public BaseLabelException(String message) {
        super(message);
    }

    public abstract HttpStatus httpStatus();
}
