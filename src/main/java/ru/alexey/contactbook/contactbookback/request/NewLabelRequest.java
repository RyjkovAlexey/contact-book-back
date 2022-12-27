package ru.alexey.contactbook.contactbookback.request;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class NewLabelRequest {
    private String name;
}
