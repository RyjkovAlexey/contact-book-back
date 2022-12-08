package ru.alexey.contactbook.contactbookback.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.alexey.contactbook.contactbookback.models.contact.Contact;

public interface ContactsRepository extends JpaRepository<Contact, Integer> {
}
