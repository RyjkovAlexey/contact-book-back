package ru.alexey.contactbook.contactbookback.dto.contactdto;


public class NewContactDetailsDTO {
    private String details;
    private String type;

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
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
