package ru.alexey.contactbook.contactbookback.model.contact;

import lombok.*;
import org.hibernate.Hibernate;
import ru.alexey.contactbook.contactbookback.model.user.Account;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@ToString
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
    private Account creator;

    @OneToMany(mappedBy = "contact")
    @ToString.Exclude
    private Set<ContactDetails> details;

    @ManyToMany(mappedBy = "contacts")
    @ToString.Exclude
    private Set<Label> labels;

    @OneToMany(mappedBy = "contact")
    @ToString.Exclude
    private Set<Note> notes;

    public Contact() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Contact contact = (Contact) o;
        return Objects.equals(id, contact.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
