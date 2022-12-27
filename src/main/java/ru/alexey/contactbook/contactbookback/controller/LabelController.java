package ru.alexey.contactbook.contactbookback.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.alexey.contactbook.contactbookback.dto.ContactDTO;
import ru.alexey.contactbook.contactbookback.dto.LabelDTO;
import ru.alexey.contactbook.contactbookback.exception.BaseLabelException;
import ru.alexey.contactbook.contactbookback.exception.LabelBadRequestException;
import ru.alexey.contactbook.contactbookback.model.contact.Contact;
import ru.alexey.contactbook.contactbookback.model.contact.Label;
import ru.alexey.contactbook.contactbookback.request.NewLabelRequest;
import ru.alexey.contactbook.contactbookback.request.UpdateLabelRequest;
import ru.alexey.contactbook.contactbookback.response.ErrorResponse;
import ru.alexey.contactbook.contactbookback.service.LabelsService;
import ru.alexey.contactbook.contactbookback.util.ErrorMessageUtils;
import ru.alexey.contactbook.contactbookback.util.LabelValidator;

import javax.validation.Valid;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/labels")
public class LabelController {
    private final LabelsService labelsService;
    private final ModelMapper modelMapper;
    private final LabelValidator labelValidator;

    @Autowired
    public LabelController(LabelsService labelsService, ModelMapper modelMapper, LabelValidator labelValidator) {
        this.labelsService = labelsService;
        this.modelMapper = modelMapper;
        this.labelValidator = labelValidator;
    }

    @GetMapping()
    public List<LabelDTO> getAll() {
        return labelsService.findAll().stream()
                .map(label -> modelMapper.map(label, LabelDTO.class)).
                collect(Collectors.toList());
    }

    @PostMapping()
    public LabelDTO save(
            @RequestBody @Valid NewLabelRequest newLabelRequest,
            BindingResult bindingResult
    ) {
        Label label = modelMapper.map(newLabelRequest, Label.class);
        labelValidator.validate(label, bindingResult);

        if (bindingResult.hasErrors()) {
            String errorMessage = ErrorMessageUtils.buildErrorMessage(bindingResult.getFieldErrors());
            throw new LabelBadRequestException(errorMessage);
        }

        return modelMapper.map(labelsService.save(label), LabelDTO.class);
    }

    @PatchMapping("/{id}")
    public LabelDTO update(
            @PathVariable int id,
            @RequestBody UpdateLabelRequest updateLabelRequest
    ) {
        Label label = modelMapper.map(updateLabelRequest, Label.class);

        Label updatedLabel = labelsService.update(id, label);

        return modelMapper.map(updatedLabel, LabelDTO.class);
    }

    @GetMapping("/contacts/{id}")
    public Set<ContactDTO> getContactWithLabel(@PathVariable int id) {
        Set<Contact> contacts = labelsService.getContactsWithLabels(id);

        return contacts.stream()
                .map(contact -> modelMapper.map(contact, ContactDTO.class))
                .collect(Collectors.toSet());
    }

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> exceptionHandler(BaseLabelException exception) {
        ErrorResponse errorResponse = new ErrorResponse(exception.getMessage(), System.currentTimeMillis());

        return new ResponseEntity<>(errorResponse, exception.httpStatus());
    }
}
