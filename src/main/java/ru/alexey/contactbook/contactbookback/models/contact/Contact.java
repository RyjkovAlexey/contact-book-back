package ru.alexey.contactbook.contactbookback.models.contact;

import jakarta.persistence.*;
import ru.alexey.contactbook.contactbookback.models.user.User;

import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "contact")
public class Contact {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "contact_name")
    private String name;

    @ManyToOne
    @JoinColumn(name = "creator_id", referencedColumnName = "id")
    private User creator;

    @OneToMany(mappedBy = "contact")
    private Set<ContactDetails> details;

    @ManyToMany(mappedBy = "contacts")
    private Set<Label> labels;

    @OneToMany(mappedBy = "contact")
    private Set<Note> notes;

    public Contact() {
    }

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

    public User getCreator() {
        return creator;
    }

    public void setCreator(User creator) {
        this.creator = creator;
    }

    public Set<ContactDetails> getDetails() {
        return details;
    }

    public void setDetails(Set<ContactDetails> details) {
        this.details = details;
    }

    public Set<Label> getLabels() {
        return labels;
    }

    public void setLabels(Set<Label> labels) {
        this.labels = labels;
    }

    public Set<Note> getNotes() {
        return notes;
    }

    public void setNotes(Set<Note> notes) {
        this.notes = notes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Contact contact = (Contact) o;
        return id == contact.id && Objects.equals(name, contact.name) && Objects.equals(creator, contact.creator);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, creator);
    }
}
