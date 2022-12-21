package ru.alexey.contactbook.contactbookback.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.alexey.contactbook.contactbookback.models.contact.Note;

@Repository
public interface NotesRepository extends JpaRepository<Note, Integer> {
}
