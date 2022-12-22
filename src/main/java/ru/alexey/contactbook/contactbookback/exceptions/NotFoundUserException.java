package ru.alexey.contactbook.contactbookback.exceptions;

import org.springframework.http.HttpStatus;

public class NotFoundUserException extends BaseUserException {
    public NotFoundUserException(int id) {
        super(String.format("unable to find optionalUser with id: %s", id));
    }

    public NotFoundUserException(String name) {
        super(String.format("unable to find optionalUser with name: %s", name));
    }

    @Override
    public HttpStatus httpStatus() {
        return HttpStatus.NOT_FOUND;
    }
}
