package ru.alexey.contactbook.contactbookback.dto.userdto;

import ru.alexey.contactbook.contactbookback.models.user.Role;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class UpdateUserDTO {
    @NotEmpty(message = "name should not be empty")
    @Size(max = 100, message = "Name cannot be more than 100 characters")
    private String name;

    @NotEmpty(message = "surname should not be empty")
    @Size(max = 100, message = "Surname cannot be more than 100 characters")
    private String surname;

    @Size(max = 100, message = "Patronymic cannot be more than 100 characters")
    private String patronymic;


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
