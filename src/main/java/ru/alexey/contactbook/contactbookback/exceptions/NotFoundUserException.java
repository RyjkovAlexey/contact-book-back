package ru.alexey.contactbook.contactbookback.exceptions;

import org.springframework.http.HttpStatus;

public class NotFoundUserException extends BaseUserException {
    public NotFoundUserException(int id) {
        super(String.format("unable to find optionalUser with id: %s", id));
    }

    @Override
    public HttpStatus httpStatus() {
        return HttpStatus.NOT_FOUND;
    }
}
