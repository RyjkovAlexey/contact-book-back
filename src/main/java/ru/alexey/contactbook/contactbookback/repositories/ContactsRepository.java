package ru.alexey.contactbook.contactbookback.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.alexey.contactbook.contactbookback.models.contact.Contact;
import ru.alexey.contactbook.contactbookback.models.contact.Label;

import java.util.List;
import java.util.Set;

public interface ContactsRepository extends JpaRepository<Contact, Integer> {
    List<Contact> getContactByLabelsContains(Label label);
}
