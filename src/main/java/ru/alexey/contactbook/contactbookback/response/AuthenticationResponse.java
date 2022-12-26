package ru.alexey.contactbook.contactbookback.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class AuthenticationResponse {
    private String accessToken;

    public AuthenticationResponse(String accessToken) {
        this.accessToken = accessToken;
    }

    public AuthenticationResponse() {
    }
}
