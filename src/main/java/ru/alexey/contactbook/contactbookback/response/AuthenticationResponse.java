package ru.alexey.contactbook.contactbookback.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.alexey.contactbook.contactbookback.dto.AccountDTO;
import ru.alexey.contactbook.contactbookback.dto.ContactDTO;

@Data
public class AuthenticationResponse {
    private String accessToken;
    private AccountDTO accountDTO;

    public AuthenticationResponse(String accessToken) {
        this.accessToken = accessToken;
    }

    public AuthenticationResponse() {
    }
}
