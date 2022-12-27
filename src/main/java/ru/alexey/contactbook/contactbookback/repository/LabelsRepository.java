package ru.alexey.contactbook.contactbookback.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.alexey.contactbook.contactbookback.model.contact.Label;

import java.util.Optional;
import java.util.Set;

@Repository
public interface LabelsRepository extends JpaRepository<Label, Integer> {
    Optional<Label> findByName(String name);
}
