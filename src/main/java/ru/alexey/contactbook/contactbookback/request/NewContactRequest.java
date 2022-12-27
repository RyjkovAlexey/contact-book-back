package ru.alexey.contactbook.contactbookback.request;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.alexey.contactbook.contactbookback.dto.*;

import java.util.Set;

@Data
@NoArgsConstructor
public class NewContactRequest {
    private String name;
    private AccountDTO creator;
    private Set<NewContactDetailsDTO> details;
    private Set<LabelDTO> labels;
    private Set<NewNoteDTO> notes;
}
