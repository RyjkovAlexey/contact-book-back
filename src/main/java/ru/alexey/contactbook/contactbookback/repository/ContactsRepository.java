package ru.alexey.contactbook.contactbookback.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.alexey.contactbook.contactbookback.model.contact.Contact;
import ru.alexey.contactbook.contactbookback.model.contact.Label;
import ru.alexey.contactbook.contactbookback.model.user.Account;

import java.util.Set;

@Repository
public interface ContactsRepository extends JpaRepository<Contact, Integer> {
    Set<Contact> findAllByLabelsContaining(Label label);

    Set<Contact> findContactsByCreator(Account creator);
}
