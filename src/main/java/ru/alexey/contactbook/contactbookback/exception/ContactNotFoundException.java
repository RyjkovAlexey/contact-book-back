package ru.alexey.contactbook.contactbookback.exception;

import org.springframework.http.HttpStatus;

public class ContactNotFoundException extends BaseContactException{
    public ContactNotFoundException(String message) {
        super(message);
    }

    @Override
    public HttpStatus httpStatus() {
        return HttpStatus.NOT_FOUND;
    }
}
