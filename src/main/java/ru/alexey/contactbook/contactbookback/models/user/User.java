package ru.alexey.contactbook.contactbookback.models.user;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import ru.alexey.contactbook.contactbookback.models.contact.Group;
import ru.alexey.contactbook.contactbookback.models.contact.Contact;

import java.util.List;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "\"user\"")
public class User {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "login")
    private String login;

    @Column(name = "password")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    @Column(name = "role")
    @Enumerated(value = EnumType.STRING)
    private Role role;

    @OneToOne(mappedBy = "user")
    @JsonBackReference
    private UserInfo info;

    @OneToMany(mappedBy = "creator")
    private Set<Contact> contacts;

    @OneToMany(mappedBy = "creator")
    private Set<Group> groups;

    @ManyToMany(mappedBy = "readPermissions")
    private Set<Group> groupsReadPermission;

    @ManyToMany(mappedBy = "writePermissions")
    private Set<Group> groupsWritePermission;

    public User() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public UserInfo getInfo() {
        return info;
    }

    public void setInfo(UserInfo info) {
        this.info = info;
    }

    public Set<Contact> getContacts() {
        return contacts;
    }

    public void setContacts(Set<Contact> contacts) {
        this.contacts = contacts;
    }

    public Set<Group> getGroups() {
        return groups;
    }

    public void setGroups(Set<Group> groups) {
        this.groups = groups;
    }

    public Set<Group> getGroupsReadPermission() {
        return groupsReadPermission;
    }

    public void setGroupsReadPermission(Set<Group> groupsReadPermission) {
        this.groupsReadPermission = groupsReadPermission;
    }

    public Set<Group> getGroupsWritePermission() {
        return groupsWritePermission;
    }

    public void setGroupsWritePermission(Set<Group> groupsWritePermission) {
        this.groupsWritePermission = groupsWritePermission;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id && Objects.equals(login, user.login) && Objects.equals(password, user.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, login, password);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", role=" + role +
                ", info=" + info +
                ", contacts=" + contacts +
                ", groups=" + groups +
                ", groupsReadPermission=" + groupsReadPermission +
                ", groupsWritePermission=" + groupsWritePermission +
                '}';
    }
}
