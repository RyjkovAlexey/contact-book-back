package ru.alexey.contactbook.contactbookback.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class NoteDTO {
    private int id;
    private String text;
}
