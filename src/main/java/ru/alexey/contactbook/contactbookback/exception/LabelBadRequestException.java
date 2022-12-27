package ru.alexey.contactbook.contactbookback.exception;

import org.springframework.http.HttpStatus;

public class LabelBadRequestException extends BaseLabelException{
    public LabelBadRequestException(String message) {
        super(message);
    }

    @Override
    public HttpStatus httpStatus() {
        return HttpStatus.BAD_REQUEST;
    }
}
