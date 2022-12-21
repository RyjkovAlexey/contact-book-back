package ru.alexey.contactbook.contactbookback.exceptions;

import org.springframework.http.HttpStatus;

public class NotFoundLabelException extends BaseLabelException{
    public NotFoundLabelException(int id) {
        super(String.format("unable to find label with id: %s",id));
    }

    @Override
    public HttpStatus httpStatus() {
        return HttpStatus.NOT_FOUND;
    }
}
