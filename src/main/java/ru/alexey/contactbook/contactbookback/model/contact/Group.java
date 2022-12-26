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
@Table(name = "\"group\"")
public class Group {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;

    @ManyToOne
    @JoinColumn(name = "creator", referencedColumnName = "id")
    private Account creator;

    @ManyToMany
    @JoinTable(
            name = "read_permission",
            joinColumns = {@JoinColumn(name = "group_id")},
            inverseJoinColumns = {@JoinColumn(name = "user_id")}
    )
    @ToString.Exclude
    private Set<Account> readPermissions;

    @ManyToMany
    @JoinTable(
            name = "write_permission",
            joinColumns = {@JoinColumn(name = "group_id")},
            inverseJoinColumns = {@JoinColumn(name = "user_id")}
    )
    @ToString.Exclude
    private Set<Account> writePermissions;

    public Group() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Group group = (Group) o;
        return Objects.equals(id, group.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
