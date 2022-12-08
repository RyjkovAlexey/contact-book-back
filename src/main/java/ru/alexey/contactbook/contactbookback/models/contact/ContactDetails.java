package ru.alexey.contactbook.contactbookback.models.contact;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "contact_details")
public class ContactDetails {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "for_contact", referencedColumnName = "id")
    private Contact contact;

    @Column(name = "details")
    private String details;

    @Column(name = "type")
    @Enumerated(value = EnumType.STRING)
    private ContactDetailType type;

    public ContactDetails() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ContactDetails that = (ContactDetails) o;
        return id == that.id && Objects.equals(contact, that.contact) && Objects.equals(details, that.details) && type == that.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, contact, details, type);
    }
}
