package ru.alexey.contactbook.contactbookback.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.alexey.contactbook.contactbookback.models.contact.Contact;
import ru.alexey.contactbook.contactbookback.models.contact.Label;
import ru.alexey.contactbook.contactbookback.models.user.User;
import ru.alexey.contactbook.contactbookback.repositories.*;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class ContactsService {
    private final ContactsRepository contactsRepository;
    private final UsersRepository usersRepository;
    private final LabelsRepository labelsRepository;
    private final NotesRepository notesRepository;
    private final ContactDetailsRepository contactDetailsRepository;

    @Autowired
    public ContactsService(ContactsRepository contactsRepository, UsersRepository usersRepository, LabelsRepository labelsRepository, NotesRepository notesRepository, ContactDetailsRepository contactDetailsRepository) {
        this.contactsRepository = contactsRepository;
        this.usersRepository = usersRepository;
        this.labelsRepository = labelsRepository;
        this.notesRepository = notesRepository;
        this.contactDetailsRepository = contactDetailsRepository;
    }

    public List<Contact> findAll() {
        return contactsRepository.findAll();
    }

    @Transactional
    public Contact save(Contact contact, int id) {
        User owner = usersRepository.getById(id);
        List<Label> labels = labelsRepository.findAllById(contact.getLabels().stream().map(Label::getId).toList());

        contact.getNotes().forEach(note -> note.setContact(contact));
        contact.getDetails().forEach(detail -> detail.setContact(contact));
        contact.setCreator(owner);
        contact.setLabels(new HashSet<>(labels));

        Contact savedContact = contactsRepository.saveAndFlush(contact);

        labels.forEach(label -> label.getContacts().add(contact));

        labelsRepository.saveAll(labels);
        contactDetailsRepository.saveAll(contact.getDetails());
        notesRepository.saveAll(contact.getNotes());

        return savedContact;
    }

    public List<Contact> findAllWithLabel(int id) {
        Optional<Label> label = labelsRepository.findById(id);
        return contactsRepository.getContactByLabelsContains(label.get());
    }

    public List<Contact> findAllWithUser(int id) {
        User owner = usersRepository.getById(id);

        return contactsRepository.getContactsByCreator(owner);
    }
}
