package ru.alexey.contactbook.contactbookback.exception;

import org.springframework.http.HttpStatus;

public class AccountNotFoundException extends BaseAccountException{
    public AccountNotFoundException(String message) {
        super(message);
    }

    @Override
    public HttpStatus httpStatus() {
        return HttpStatus.NOT_FOUND;
    }
}
