package ru.alexey.contactbook.contactbookback.exceptions;

import org.springframework.http.HttpStatus;

public class UserNotUpdatedException extends BaseUserException {

    public UserNotUpdatedException(String message) {
        super(message);
    }

    @Override
    public HttpStatus httpStatus() {
        return HttpStatus.BAD_REQUEST;
    }
}
