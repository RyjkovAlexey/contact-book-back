package ru.alexey.contactbook.contactbookback.exception;

import org.springframework.http.HttpStatus;

public class DepartmentNotFoundException extends BaseDepartmentException{
    public DepartmentNotFoundException(String message) {
        super(message);
    }

    @Override
    public HttpStatus httpStatus() {
        return HttpStatus.NOT_FOUND;
    }
}
