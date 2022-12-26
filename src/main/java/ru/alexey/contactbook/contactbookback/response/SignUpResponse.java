package ru.alexey.contactbook.contactbookback.response;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.alexey.contactbook.contactbookback.dto.RoleDTO;
import ru.alexey.contactbook.contactbookback.model.user.Role;

import java.util.Set;

@Data
@NoArgsConstructor
public class SignUpResponse {
    private int id;
    private String username;
    private Set<RoleDTO> roles;
    private String name;
    private String surname;
    private String patronymic;
}
