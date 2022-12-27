package ru.alexey.contactbook.contactbookback.request;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UpdateAccountRequest {
    private String name;
    private String surname;
    private String patronymic;
}
