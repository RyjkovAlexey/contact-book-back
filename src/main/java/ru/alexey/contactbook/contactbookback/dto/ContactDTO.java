package ru.alexey.contactbook.contactbookback.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
public class ContactDTO {
    private int id;
    private String name;
    private AccountDTO creator;
    private Set<ContactDetailsDTO> details;
    private Set<LabelDTO> labels;
}
