package ru.alexey.contactbook.contactbookback.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.alexey.contactbook.contactbookback.dto.ContactDTO;
import ru.alexey.contactbook.contactbookback.model.contact.Contact;
import ru.alexey.contactbook.contactbookback.request.NewContactRequest;
import ru.alexey.contactbook.contactbookback.request.UpdateContactRequest;
import ru.alexey.contactbook.contactbookback.service.ContactsService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/contacts")
public class ContactController {
    private final ContactsService contactsService;
    private final ModelMapper modelMapper;

    @Autowired
    public ContactController(ContactsService contactsService, ModelMapper modelMapper) {
        this.contactsService = contactsService;
        this.modelMapper = modelMapper;
    }

    @GetMapping()
    @PreAuthorize("hasRole('ADMIN')")
    public List<ContactDTO> getAll() {
        return contactsService.findAll().stream()
                .map(contact -> modelMapper.map(contact, ContactDTO.class))
                .collect(Collectors.toList());
    }

    @PostMapping()
    public ContactDTO save(@RequestBody NewContactRequest newContactRequest) {
        Contact contact = modelMapper.map(newContactRequest, Contact.class);

        Contact savedContact = contactsService.save(contact);

        return modelMapper.map(savedContact, ContactDTO.class);
    }

    @PatchMapping("/{id}")
    public ContactDTO update(
            @PathVariable int id,
            @RequestBody UpdateContactRequest updateContactRequest
    ) {
        Contact contact = modelMapper.map(updateContactRequest, Contact.class);

        Contact updatedContact = contactsService.update(id, contact);

        return modelMapper.map(updatedContact, ContactDTO.class);
    }

    @GetMapping("/{id}")
    public ContactDTO getById(@PathVariable int id) {
        Contact contact = contactsService.findById(id);

        return modelMapper.map(contact, ContactDTO.class);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        contactsService.deleteById(id);
    }
}
