package ru.alexey.contactbook.contactbookback.request;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
public class SignUpRequest {

    @NotNull
    @Size(min = 6, max = 100)
    private String username;

    @NotNull
    @Size(min = 6, max = 100)
    private String password;

    @NotNull
    @Size(min = 1, max = 100)
    private String name;

    @NotNull
    @Size(min = 1, max = 100)
    private String surname;

    @NotNull
    @Size(min = 0, max = 100)
    private String patronymic;
}
