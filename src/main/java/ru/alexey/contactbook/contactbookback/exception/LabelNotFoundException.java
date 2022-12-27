package ru.alexey.contactbook.contactbookback.exception;

import org.springframework.http.HttpStatus;

public class LabelNotFoundException extends BaseLabelException{
    public LabelNotFoundException(String message) {
        super(message);
    }

    @Override
    public HttpStatus httpStatus() {
        return HttpStatus.NOT_FOUND;
    }
}
