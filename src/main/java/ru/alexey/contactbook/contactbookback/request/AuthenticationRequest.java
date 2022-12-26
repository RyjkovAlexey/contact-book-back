package ru.alexey.contactbook.contactbookback.request;


import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data

public class AuthenticationRequest {
    @NotNull
    @Size(max = 255)
    private String username;

    @NotNull
    @Size(max = 255)
    private String password;

    public AuthenticationRequest() {
    }

    public AuthenticationRequest(String login, String password) {
        this.username = login;
        this.password = password;
    }
}
