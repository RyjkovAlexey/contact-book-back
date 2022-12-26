package ru.alexey.contactbook.contactbookback.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.alexey.contactbook.contactbookback.model.contact.ContactDetails;

@Repository
public interface ContactsDetailsRepository extends JpaRepository<ContactDetails, Integer> {
}
