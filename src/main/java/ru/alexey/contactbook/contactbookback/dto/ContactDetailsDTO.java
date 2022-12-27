package ru.alexey.contactbook.contactbookback.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class ContactDetailsDTO {
    private int id;
    private String details;
    private String type;
}
