package ru.alexey.contactbook.contactbookback.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.alexey.contactbook.contactbookback.exception.LabelNotFoundException;
import ru.alexey.contactbook.contactbookback.model.contact.Contact;
import ru.alexey.contactbook.contactbookback.model.contact.Label;
import ru.alexey.contactbook.contactbookback.repository.ContactsRepository;
import ru.alexey.contactbook.contactbookback.repository.LabelsRepository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@Transactional(readOnly = true)
public class LabelsService {
    private final LabelsRepository labelsRepository;
    private final ContactsRepository contactsRepository;

    @Autowired
    public LabelsService(LabelsRepository labelsRepository, ContactsRepository contactsRepository) {
        this.labelsRepository = labelsRepository;
        this.contactsRepository = contactsRepository;
    }

    public List<Label> findAll() {
        return labelsRepository.findAll();
    }

    public Optional<Label> findByName(String name) {
        return labelsRepository.findByName(name);
    }

    @Transactional
    public Label save(Label label) {
        return labelsRepository.saveAndFlush(label);
    }

    public Set<Contact> getContactsWithLabels(int id) {
        Label label = labelsRepository.findById(id)
                .orElseThrow(() -> new LabelNotFoundException(String.format("Label with id: %s not found", id)));
        return contactsRepository.findAllByLabelsContaining(label);
    }

    @Transactional
    public Label update(int id, Label label) {
        Label labelFromDatabase = labelsRepository.findById(id)
                .orElseThrow(() -> new LabelNotFoundException(String.format("Label with id: %s not found", id)));

        labelFromDatabase.setName(label.getName());

        return labelsRepository.saveAndFlush(labelFromDatabase);
    }
}
