package ru.alexey.contactbook.contactbookback.models.contact;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "note")
public class Note {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "text")
    private String text;

    @ManyToOne
    @JoinColumn(name = "for_contact", referencedColumnName = "id")
    private Contact contact;

    public Note() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Note note = (Note) o;
        return id == note.id && Objects.equals(text, note.text) && Objects.equals(contact, note.contact);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, text, contact);
    }
}
