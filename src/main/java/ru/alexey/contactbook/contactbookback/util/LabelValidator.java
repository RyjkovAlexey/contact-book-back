package ru.alexey.contactbook.contactbookback.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.alexey.contactbook.contactbookback.model.contact.Label;
import ru.alexey.contactbook.contactbookback.service.LabelsService;

@Component
public class LabelValidator implements Validator {
    private final LabelsService labelsService;

    @Autowired
    public LabelValidator(LabelsService labelsService) {
        this.labelsService = labelsService;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return Label.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Label label = (Label) o;

        if (labelsService.findByName(label.getName()).isPresent())
            errors.reject(String.format("label %s already exists", label.getName()));
    }
}
