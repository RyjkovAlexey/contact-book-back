package ru.alexey.contactbook.contactbookback.utils;

import org.springframework.validation.FieldError;

import java.util.List;

public class ErrorMessageUtils {
    public static String buildErrorMessage(List<FieldError> fieldErrors) {
        StringBuilder errorMessage = new StringBuilder();

        fieldErrors.forEach(error -> {
            errorMessage.append(error.getField())
                    .append(" - ")
                    .append(error.getDefaultMessage())
                    .append(";\n");
        });

        return errorMessage.toString();
    }
}
