package ru.alexey.contactbook.contactbookback.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

import java.util.Set;

@Data
@NoArgsConstructor
public class AccountDTO {
    private int id;
    private String username;
    private String name;
    private String surname;
    private String patronymic;
    @Nullable
    private DepartmentDTO department;
    private Set<RoleDTO> roles;
}
