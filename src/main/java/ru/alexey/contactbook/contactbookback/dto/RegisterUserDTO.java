package ru.alexey.contactbook.contactbookback.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class RegisterUserDTO {
    @NotEmpty(message = "login should not be empty")
    @Size(min = 6, max = 100, message = "Login length must be more than 6 and not more than 100 characters")
    private String login;

    @NotEmpty(message = "password should not be empty")
    @Size(min = 6, message = "password must be at least 6 characters long")
    private String password;

    @NotEmpty(message = "name should not be empty")
    @Size(max = 100, message = "Name cannot be more than 100 characters")
    private String name;
    @NotEmpty(message = "surname should not be empty")
    @Size(max = 100, message = "Surname cannot be more than 100 characters")
    private String surname;

    @Size(max = 100, message = "Patronymic cannot be more than 100 characters")
    private String patronymic;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }
}
