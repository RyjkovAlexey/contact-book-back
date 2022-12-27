package ru.alexey.contactbook.contactbookback.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.alexey.contactbook.contactbookback.exception.ContactNotFoundException;
import ru.alexey.contactbook.contactbookback.model.contact.Contact;
import ru.alexey.contactbook.contactbookback.repository.ContactsDetailsRepository;
import ru.alexey.contactbook.contactbookback.repository.ContactsRepository;
import ru.alexey.contactbook.contactbookback.repository.LabelsRepository;
import ru.alexey.contactbook.contactbookback.repository.NotesRepository;

import java.util.HashSet;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class ContactsService {
    private final ContactsRepository contactsRepository;
    private final ContactsDetailsRepository contactsDetailsRepository;
    private final LabelsRepository labelsRepository;
    private final NotesRepository notesRepository;

    @Autowired
    public ContactsService(ContactsRepository contactsRepository, ContactsDetailsRepository contactsDetailsRepository, LabelsRepository labelsRepository, NotesRepository notesRepository) {
        this.contactsRepository = contactsRepository;
        this.contactsDetailsRepository = contactsDetailsRepository;
        this.labelsRepository = labelsRepository;
        this.notesRepository = notesRepository;
    }

    public List<Contact> findAll() {
        return contactsRepository.findAll();
    }

    @Transactional
    public Contact save(Contact contact) {
        Contact savedContact = contactsRepository.saveAndFlush(contact);

        contact.getDetails().forEach(detail -> detail.setContact(savedContact));
        contact.getNotes().forEach(note -> note.setContact(savedContact));
        contact.getLabels().forEach(label -> label.getContacts().add(savedContact));

        savedContact.setNotes(new HashSet<>(notesRepository.saveAllAndFlush(contact.getNotes())));
        savedContact.setDetails(new HashSet<>(contactsDetailsRepository.saveAllAndFlush(contact.getDetails())));
        savedContact.setLabels(new HashSet<>(labelsRepository.saveAllAndFlush(contact.getLabels())));


        return contactsRepository.saveAndFlush(savedContact);
    }

    @Transactional
    public Contact update(int id, Contact contact) {
        Contact contactFromDatabase = contactsRepository.getById(id);

        contactFromDatabase.setName(contact.getName());

        return contactsRepository.saveAndFlush(contactFromDatabase);
    }

    public Contact findById(int id) {
        return contactsRepository.findById(id)
                .orElseThrow(() -> new ContactNotFoundException(String.format("Contact with id %s not found", id)));
    }

    @Transactional
    public void deleteById(int id) {
        contactsRepository.deleteById(id);
    }
}
