package ru.alexey.contactbook.contactbookback.dto.contactdto;

import ru.alexey.contactbook.contactbookback.dto.label.LabelDTO;

import java.util.Objects;
import java.util.Set;

public class ContactDTO {
    private int id;
    private String name;
    private int creator;
    private Set<ContactDetailsDTO> details = Set.of();
    private Set<LabelDTO> labels = Set.of();
    private Set<NoteDTO> notes = Set.of();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCreator() {
        return creator;
    }

    public void setCreator(int creator) {
        this.creator = creator;
    }

    public Set<ContactDetailsDTO> getDetails() {
        return details;
    }

    public void setDetails(Set<ContactDetailsDTO> details) {
        this.details = details;
    }

    public Set<LabelDTO> getLabels() {
        return labels;
    }

    public void setLabels(Set<LabelDTO> labels) {
        this.labels = labels;
    }

    public Set<NoteDTO> getNotes() {
        return notes;
    }

    public void setNotes(Set<NoteDTO> notes) {
        this.notes = notes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ContactDTO that = (ContactDTO) o;

        if (id != that.id) return false;
        if (creator != that.creator) return false;
        if (!Objects.equals(name, that.name)) return false;
        if (!Objects.equals(details, that.details)) return false;
        if (!Objects.equals(labels, that.labels)) return false;
        return Objects.equals(notes, that.notes);
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + creator;
        result = 31 * result + (details != null ? details.hashCode() : 0);
        result = 31 * result + (labels != null ? labels.hashCode() : 0);
        result = 31 * result + (notes != null ? notes.hashCode() : 0);
        return result;
    }
}
