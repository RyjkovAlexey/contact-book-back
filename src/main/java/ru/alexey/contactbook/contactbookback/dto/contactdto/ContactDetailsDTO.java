package ru.alexey.contactbook.contactbookback.dto.contactdto;

import ru.alexey.contactbook.contactbookback.models.contact.ContactDetailType;

public class ContactDetailsDTO {
    private int id;
    private String details;
    private ContactDetailType type;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public ContactDetailType getType() {
        return type;
    }

    public void setType(ContactDetailType type) {
        this.type = type;
    }
}
