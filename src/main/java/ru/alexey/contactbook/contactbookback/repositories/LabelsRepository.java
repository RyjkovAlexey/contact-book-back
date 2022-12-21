package ru.alexey.contactbook.contactbookback.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.alexey.contactbook.contactbookback.models.contact.Label;

import java.util.Optional;

@Repository
public interface LabelsRepository extends JpaRepository<Label, Integer> {
    Optional<Label> findByName (String name);
}
