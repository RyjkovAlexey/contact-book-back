package ru.alexey.contactbook.contactbookback.model.contact;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@ToString
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
    @ToString.Exclude
    private Set<Contact> contacts;

    public Label() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Label label = (Label) o;
        return Objects.equals(id, label.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
