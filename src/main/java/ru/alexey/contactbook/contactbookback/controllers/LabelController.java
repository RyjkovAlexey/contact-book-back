package ru.alexey.contactbook.contactbookback.controllers;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.alexey.contactbook.contactbookback.dto.label.NewLabelDTO;
import ru.alexey.contactbook.contactbookback.dto.label.LabelDTO;
import ru.alexey.contactbook.contactbookback.dto.label.UpdateLabelDTO;
import ru.alexey.contactbook.contactbookback.exceptions.BaseLabelException;
import ru.alexey.contactbook.contactbookback.models.contact.Label;
import ru.alexey.contactbook.contactbookback.services.LabelsService;
import ru.alexey.contactbook.contactbookback.utils.ErrorResponse;
import ru.alexey.contactbook.contactbookback.utils.ModelUtils;
import org.slf4j.Logger;

import java.net.http.HttpResponse;
import java.util.List;

@RestController
@RequestMapping("/labels")
public class LabelController {
    private final LabelsService labelsService;
    private final Logger logger = LoggerFactory.getLogger(LabelController.class);

    @Autowired
    public LabelController(LabelsService labelsService) {
        this.labelsService = labelsService;
    }

    @GetMapping()
    public ResponseEntity<List<LabelDTO>> getAll() {
        List<Label> labels = labelsService.findAll();

        List<LabelDTO> dtos = labels
                .stream()
                .map(label -> ModelUtils.convertToDTO(label, LabelDTO.class))
                .toList();

        return ResponseEntity.ok(dtos);
    }

    @PostMapping()
    public ResponseEntity<LabelDTO> create(
            @RequestBody NewLabelDTO newLabelDTO
    ) {
        Label newLabel = ModelUtils.convertToModel(newLabelDTO, Label.class);

        return new ResponseEntity<>(
                ModelUtils.convertToDTO(labelsService.save(newLabel), LabelDTO.class),
                HttpStatus.CREATED
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<LabelDTO> getById(@PathVariable int id) {
        LabelDTO labelDTO = ModelUtils.convertToDTO(labelsService.findById(id), LabelDTO.class);

        return ResponseEntity.ok(labelDTO);
    }

    @PatchMapping()
    public ResponseEntity<LabelDTO> update(
            @RequestBody UpdateLabelDTO updateLabelDTO
    ) {
        Label label = ModelUtils.convertToModel(updateLabelDTO, Label.class);

        Label updatedLabel = ModelUtils.convertToDTO(labelsService.update(label), Label.class);

        return ResponseEntity.ok(ModelUtils.convertToDTO(updatedLabel, LabelDTO.class));
    }

    @ExceptionHandler
    private ResponseEntity<ErrorResponse> exceptionHandler(BaseLabelException exp) {
        ErrorResponse response = new ErrorResponse(
                exp.getMessage(),
                System.currentTimeMillis()
        );

        return new ResponseEntity<>(response, exp.httpStatus());
    }
}
