package ru.alexey.contactbook.contactbookback.exceptions;

import org.springframework.http.HttpStatus;

public class UserNotCreatedException extends BaseUserException {
    public UserNotCreatedException(String message) {
        super(message);
    }

    @Override
    public HttpStatus httpStatus() {
        return HttpStatus.BAD_REQUEST;
    }
}
