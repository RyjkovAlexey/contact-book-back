package ru.alexey.contactbook.contactbookback.exception;

import org.springframework.http.HttpStatus;

public class LabelAlreadyExistsException extends BaseLabelException {
    public LabelAlreadyExistsException(String labelName) {
        super(String.format("name label: %s already exists", labelName));
    }

    @Override
    public HttpStatus httpStatus() {
        return HttpStatus.BAD_REQUEST;
    }
}