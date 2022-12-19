package ru.alexey.contactbook.contactbookback.models.contact;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "label")
public class Label {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;

    @ManyToMany
    @JoinTable(
            name = "contact_label",
            joinColumns = {@JoinColumn(name = "label_id")},
            inverseJoinColumns = {@JoinColumn(name = "contact_id")}
    )
    private Set<Contact> contacts;

    public Label() {
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

    public Set<Contact> getContacts() {
        return contacts;
    }

    public void setContacts(Set<Contact> contacts) {
        this.contacts = contacts;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Label label = (Label) o;
        return id == label.id && Objects.equals(name, label.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
