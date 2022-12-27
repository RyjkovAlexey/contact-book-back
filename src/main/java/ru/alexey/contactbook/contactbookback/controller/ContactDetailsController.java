package ru.alexey.contactbook.contactbookback.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.alexey.contactbook.contactbookback.dto.ContactDTO;
import ru.alexey.contactbook.contactbookback.dto.ContactDetailsDTO;
import ru.alexey.contactbook.contactbookback.dto.NewContactDetailsDTO;
import ru.alexey.contactbook.contactbookback.model.contact.Contact;
import ru.alexey.contactbook.contactbookback.model.contact.ContactDetails;
import ru.alexey.contactbook.contactbookback.service.ContactsDetailsService;

@RestController
@RequestMapping("/contacts/details")
public class ContactDetailsController {
    private final ContactsDetailsService contactsDetailsService;
    private final ModelMapper modelMapper;

    @Autowired
    public ContactDetailsController(ContactsDetailsService contactsDetailsService, ModelMapper modelMapper) {
        this.contactsDetailsService = contactsDetailsService;
        this.modelMapper = modelMapper;
    }

    @PostMapping("/{id}")
    public ContactDTO addDetail(
            @PathVariable int id,
            @RequestBody NewContactDetailsDTO newContactDetailsDTO
    ) {
        ContactDetails contactDetails = modelMapper.map(newContactDetailsDTO, ContactDetails.class);

        Contact savedContact = contactsDetailsService.addNewDetailsToContact(id, contactDetails);

        return modelMapper.map(savedContact, ContactDTO.class);
    }

    @DeleteMapping("/{id}")
    public void deleteDetails(
            @PathVariable int id
    ) {
        contactsDetailsService.deleteDetails(id);
    }

    @PatchMapping("/{id}")
    public void updateDetails(
            @PathVariable int id,
            @RequestBody ContactDetailsDTO contactDetailsDTO
    ) {
        ContactDetails contactDetails = modelMapper.map(contactDetailsDTO, ContactDetails.class);

        contactsDetailsService.updateDetails(id, contactDetails);
    }
}
