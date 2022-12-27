package ru.alexey.contactbook.contactbookback.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class NewContactDetailsDTO {
    private String details;
    private String type;
}
