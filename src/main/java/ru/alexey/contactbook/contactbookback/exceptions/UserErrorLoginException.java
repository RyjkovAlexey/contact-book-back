package ru.alexey.contactbook.contactbookback.exceptions;

import org.springframework.http.HttpStatus;

public class UserErrorLoginException extends BaseUserException {
    public UserErrorLoginException(String message) {
        super(message);
    }

    @Override
    public HttpStatus httpStatus() {
        return HttpStatus.FORBIDDEN;
    }
}
