package ru.alexey.contactbook.contactbookback.model.user;

import lombok.*;
import org.hibernate.Hibernate;
import org.springframework.lang.Nullable;
import ru.alexey.contactbook.contactbookback.model.contact.Contact;
import ru.alexey.contactbook.contactbookback.model.contact.Group;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@ToString
@Entity
@Table(name = "\"account\"")
public class Account {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "login")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    @Column(name = "patronymic")
    private String patronymic;

    @ManyToOne
    @JoinColumn(name = "department", referencedColumnName = "id")
    @Nullable
    private Department department;

    @OneToMany(mappedBy = "creator")
    @ToString.Exclude
    private Set<Contact> contacts = new HashSet<>();

    @OneToMany(mappedBy = "creator")
    @ToString.Exclude
    private Set<Group> groups = new HashSet<>();

    @ManyToMany(mappedBy = "readPermissions")
    @ToString.Exclude
    private Set<Group> groupsReadPermission = new HashSet<>();

    @ManyToMany(mappedBy = "writePermissions")
    @ToString.Exclude
    private Set<Group> groupsWritePermission = new HashSet<>();

    @ManyToMany(mappedBy = "accounts", fetch = FetchType.EAGER)
    @ToString.Exclude
    private Set<Role> roles = new HashSet<>();

    public Account() {
    }

    public Account(String username, String password, String name, String surname, String patronymic, Department department) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.patronymic = patronymic;
        this.department = department;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Account account = (Account) o;
        return Objects.equals(id, account.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
