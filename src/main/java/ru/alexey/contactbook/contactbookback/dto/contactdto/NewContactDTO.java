package ru.alexey.contactbook.contactbookback.dto.contactdto;

import ru.alexey.contactbook.contactbookback.dto.label.LabelDTO;

import java.util.HashSet;
import java.util.Set;

public class NewContactDTO{
    private String name;
    private Set<NewContactDetailsDTO> details = new HashSet<>();
    private Set<LabelDTO> labels = new HashSet<>();
    private Set<NoteDTO> notes = new HashSet<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<NewContactDetailsDTO> getDetails() {
        return details;
    }

    public void setDetails(Set<NewContactDetailsDTO> details) {
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
    public String toString() {
        return "NewContactDTO{" +
                "name='" + name + '\'' +
                ", details=" + details +
                ", labels=" + labels +
                ", notes=" + notes +
                '}';
    }
}
