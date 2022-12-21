package ru.alexey.contactbook.contactbookback.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.alexey.contactbook.contactbookback.dto.contactdto.ContactDTO;
import ru.alexey.contactbook.contactbookback.dto.contactdto.ContactDetailsDTO;
import ru.alexey.contactbook.contactbookback.dto.contactdto.NewContactDTO;
import ru.alexey.contactbook.contactbookback.dto.contactdto.NoteDTO;
import ru.alexey.contactbook.contactbookback.dto.label.LabelDTO;
import ru.alexey.contactbook.contactbookback.models.contact.Contact;
import ru.alexey.contactbook.contactbookback.services.ContactsService;
import ru.alexey.contactbook.contactbookback.utils.ModelUtils;

import java.net.http.HttpResponse;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/contacts")
public class ContactsController {
    private final ContactsService contactService;

    @Autowired
    public ContactsController(ContactsService contactService) {
        this.contactService = contactService;
    }

    @GetMapping()
    public ResponseEntity<List<ContactDTO>> getAll() {
        List<Contact> contacts = contactService.findAll();

        List<ContactDTO> dtos = contacts.stream().map(this::convertToDTO).toList();

        return new ResponseEntity<>(dtos, HttpStatus.FOUND);
    }

    @PostMapping
    public ResponseEntity<ContactDTO> create(
            @RequestBody NewContactDTO newContactDTO,
            @RequestParam("creatorId") int id
    ) {
        Contact contact = ModelUtils.convertToModel(newContactDTO, Contact.class);

        System.out.println(contact);

        Contact savedContact = contactService.save(contact, id);

        return ResponseEntity.ok(convertToDTO(savedContact));
    }

    @GetMapping("/getWithLabel/{id}")
    public ResponseEntity<List<ContactDTO>> getContactWithLabel(
            @PathVariable int id
    ) {
        List<Contact> contacts = contactService.findAllWithLabel(id);
        List<ContactDTO> contactDTOS = contacts.stream().map(this::convertToDTO).toList();

        return ResponseEntity.ok(contactDTOS);
    }

    private ContactDTO convertToDTO(Contact contact) {
        ContactDTO contactDTO = new ContactDTO();

        Set<LabelDTO> labels = contact.getLabels().stream()
                .map(label -> ModelUtils.convertToDTO(label, LabelDTO.class))
                .collect(Collectors.toSet());

        Set<ContactDetailsDTO> details = contact.getDetails().stream()
                .map(detail -> ModelUtils.convertToDTO(detail, ContactDetailsDTO.class))
                .collect(Collectors.toSet());

        Set<NoteDTO> notes = contact.getNotes().stream()
                .map(note -> ModelUtils.convertToDTO(note, NoteDTO.class))
                .collect(Collectors.toSet());

        contactDTO.setId(contact.getId());
        contactDTO.setName(contact.getName());
        contactDTO.setCreator(contact.getCreator().getId());
        contactDTO.setLabels(labels);
        contactDTO.setDetails(details);
        contactDTO.setNotes(notes);

        return contactDTO;
    }
}
