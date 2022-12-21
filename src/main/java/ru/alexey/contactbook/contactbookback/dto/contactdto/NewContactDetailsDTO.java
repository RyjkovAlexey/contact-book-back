package ru.alexey.contactbook.contactbookback.dto.contactdto;

import ru.alexey.contactbook.contactbookback.models.contact.ContactDetailType;

public class NewContactDetailsDTO {
    private String details;
    private ContactDetailType type;

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

    @Override
    public String toString() {
        return "NewContactDetailsDTO{" +
                "details='" + details + '\'' +
                ", type=" + type +
                '}';
    }
}
