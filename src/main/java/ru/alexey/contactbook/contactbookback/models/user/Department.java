package ru.alexey.contactbook.contactbookback.models.user;

import jakarta.persistence.*;
import ru.alexey.contactbook.contactbookback.models.user.UserInfo;

import java.util.List;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "department")
public class Department {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "department")
    private Set<UserInfo> usersInDepartment;

    public Department() {
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

    public Set<UserInfo> getUsersInDepartment() {
        return usersInDepartment;
    }

    public void setUsersInDepartment(Set<UserInfo> usersInDepartment) {
        this.usersInDepartment = usersInDepartment;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Department that = (Department) o;
        return id == that.id && Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
