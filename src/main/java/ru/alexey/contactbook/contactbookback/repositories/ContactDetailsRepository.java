package ru.alexey.contactbook.contactbookback.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.alexey.contactbook.contactbookback.models.contact.ContactDetails;

@Repository
public interface ContactDetailsRepository extends JpaRepository<ContactDetails, Integer> {
}
