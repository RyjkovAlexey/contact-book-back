package ru.alexey.contactbook.contactbookback.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.alexey.contactbook.contactbookback.model.contact.Contact;
import ru.alexey.contactbook.contactbookback.model.contact.ContactDetails;
import ru.alexey.contactbook.contactbookback.repository.ContactsDetailsRepository;
import ru.alexey.contactbook.contactbookback.repository.ContactsRepository;

@Service
@Transactional(readOnly = true)
public class ContactsDetailsService {
    private final ContactsDetailsRepository contactsDetailsRepository;
    private final ContactsRepository contactsRepository;

    @Autowired
    public ContactsDetailsService(ContactsDetailsRepository contactsDetailsRepository, ContactsRepository contactsRepository) {
        this.contactsDetailsRepository = contactsDetailsRepository;
        this.contactsRepository = contactsRepository;
    }

    @Transactional
    public Contact addNewDetailsToContact(int id, ContactDetails contactDetails) {
        Contact contact = contactsRepository.getById(id);

        contactDetails.setContact(contact);

        ContactDetails savedDetails = contactsDetailsRepository.saveAndFlush(contactDetails);

        contact.getDetails().add(savedDetails);

        return contactsRepository.saveAndFlush(contact);
    }

    public void deleteDetails(int detailsId) {
        contactsDetailsRepository.deleteById(detailsId);
    }

    public void updateDetails(int id, ContactDetails contactDetails) {
        contactsDetailsRepository.save(contactDetails);
    }
}
