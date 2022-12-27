package ru.alexey.contactbook.contactbookback.request;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UpdateLabelRequest {
    private int id;
    private String name;
}
