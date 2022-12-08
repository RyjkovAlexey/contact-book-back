package ru.alexey.contactbook.contactbookback.models.contact;

import jakarta.persistence.*;
import ru.alexey.contactbook.contactbookback.models.user.User;

import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "group")
public class Group {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;

    @ManyToOne
    @JoinColumn(name = "creator", referencedColumnName = "id")
    private User creator;

    @ManyToMany
    @JoinTable(
            name = "read_permission",
            joinColumns = {@JoinColumn(name = "group_id")},
            inverseJoinColumns = {@JoinColumn(name = "user_id")}
    )
    private Set<User> readPermissions;

    @ManyToMany
    @JoinTable(
            name = "write_permission",
            joinColumns = {@JoinColumn(name = "group_id")},
            inverseJoinColumns = {@JoinColumn(name = "user_id")}
    )
    private Set<User> writePermissions;

    public Group() {
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

    public Set<User> getReadPermissions() {
        return readPermissions;
    }

    public void setReadPermissions(Set<User> readPermissions) {
        this.readPermissions = readPermissions;
    }

    public Set<User> getWritePermissions() {
        return writePermissions;
    }

    public void setWritePermissions(Set<User> writePermissions) {
        this.writePermissions = writePermissions;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Group group = (Group) o;
        return id == group.id && Objects.equals(name, group.name) && Objects.equals(creator, group.creator);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, creator);
    }
}
