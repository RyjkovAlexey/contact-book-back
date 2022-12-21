package ru.alexey.contactbook.contactbookback.dto.userdto;

import ru.alexey.contactbook.contactbookback.models.user.Role;

public class NewUserDTO {
    private int id;
    private String login;
    private Role role;

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

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
