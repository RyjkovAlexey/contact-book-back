package ru.alexey.contactbook.contactbookback.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.alexey.contactbook.contactbookback.model.contact.Group;

@Repository
public interface GroupRepository extends JpaRepository<Group, Integer> {
}
