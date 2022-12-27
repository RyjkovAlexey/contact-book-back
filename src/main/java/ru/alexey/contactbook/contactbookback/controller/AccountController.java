package ru.alexey.contactbook.contactbookback.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.alexey.contactbook.contactbookback.dto.AccountDTO;
import ru.alexey.contactbook.contactbookback.dto.ContactDTO;
import ru.alexey.contactbook.contactbookback.model.contact.Contact;
import ru.alexey.contactbook.contactbookback.model.user.Account;
import ru.alexey.contactbook.contactbookback.request.UpdateAccountRequest;
import ru.alexey.contactbook.contactbookback.service.AccountsService;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/accounts")
public class AccountController {
    private final AccountsService accountsService;
    private final ModelMapper modelMapper;

    @Autowired
    public AccountController(AccountsService accountsService, ModelMapper modelMapper) {
        this.accountsService = accountsService;
        this.modelMapper = modelMapper;
    }

    @GetMapping()
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public List<AccountDTO> getAccounts() {
        List<Account> accounts = accountsService.findAll();

        return accounts.stream()
                .map(account -> modelMapper.map(account, AccountDTO.class))
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public AccountDTO getAccount(@PathVariable int id) {
        Account account = accountsService.findById(id);

        return modelMapper.map(account, AccountDTO.class);
    }

    @PatchMapping("/{id}")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public AccountDTO updateAccount(
            @RequestBody UpdateAccountRequest updateAccountRequest,
            @PathVariable int id
    ) {
        Account account = accountsService.update(id, modelMapper.map(updateAccountRequest, Account.class));

        return modelMapper.map(account, AccountDTO.class);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteAccount(@PathVariable int id) {
        accountsService.delete(id);
    }

    @GetMapping("/contacts/{id}")
    public Set<ContactDTO> getAllContactsByAccount(@PathVariable int id) {
        Set<Contact> contacts = accountsService.findContactsForAccount(id);

        return contacts.stream()
                .map(contact -> modelMapper.map(contact, ContactDTO.class))
                .collect(Collectors.toSet());
    }
}
