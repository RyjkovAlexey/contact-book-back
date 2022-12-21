package ru.alexey.contactbook.contactbookback.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.alexey.contactbook.contactbookback.dto.label.LabelDTO;
import ru.alexey.contactbook.contactbookback.exceptions.LabelAlreadyExistsException;
import ru.alexey.contactbook.contactbookback.exceptions.NotFoundLabelException;
import ru.alexey.contactbook.contactbookback.models.contact.Label;
import ru.alexey.contactbook.contactbookback.repositories.LabelsRepository;

import java.util.List;
import java.util.Optional;

@Service
public class LabelsService {

    private final LabelsRepository labelsRepository;

    @Autowired
    public LabelsService(LabelsRepository labelsRepository) {
        this.labelsRepository = labelsRepository;
    }

    public List<Label> findAll() {
        return labelsRepository.findAll();
    }

    @Transactional
    public Label save(Label newLabel) {
        if (labelExist(newLabel.getName()))
            throw new LabelAlreadyExistsException(newLabel.getName());

        return labelsRepository.saveAndFlush(newLabel);
    }

    public Label findById(int id) {
        Optional<Label> label = labelsRepository.findById(id);

        label.orElseThrow(() -> new NotFoundLabelException(id));

        return label.get();
    }

    @Transactional
    public Label update(Label updatableLabel) {
        Optional<Label> label = labelsRepository.findById(updatableLabel.getId());

        label.orElseThrow(() -> new NotFoundLabelException(updatableLabel.getId()));

        if (!labelExist(updatableLabel.getName())) {
            return labelsRepository.save(updatableLabel);
        } else {
            throw new LabelAlreadyExistsException(updatableLabel.getName());
        }
    }

    private boolean labelExist(String name) {
        return labelsRepository.findByName(name).isPresent();
    }
}
