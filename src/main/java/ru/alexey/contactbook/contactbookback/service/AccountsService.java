package ru.alexey.contactbook.contactbookback.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.alexey.contactbook.contactbookback.dto.ContactDTO;
import ru.alexey.contactbook.contactbookback.model.contact.Contact;
import ru.alexey.contactbook.contactbookback.model.user.Account;
import ru.alexey.contactbook.contactbookback.model.user.Role;
import ru.alexey.contactbook.contactbookback.repository.AccountsRepository;
import ru.alexey.contactbook.contactbookback.repository.ContactsRepository;
import ru.alexey.contactbook.contactbookback.repository.RolesRepository;

import java.util.List;
import java.util.Set;

@Service
@Transactional(readOnly = true)
public class AccountsService {
    private final AccountsRepository accountsRepository;
    private final ContactsRepository contactsRepository;
    private final RolesRepository rolesRepository;

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AccountsService(AccountsRepository accountsRepository, ContactsRepository contactsRepository, RolesRepository rolesRepository, PasswordEncoder passwordEncoder) {
        this.accountsRepository = accountsRepository;
        this.contactsRepository = contactsRepository;
        this.rolesRepository = rolesRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public Account save(Account account) {
        Role role = rolesRepository.getRoleByName("ROLE_USER");

        account.setRoles(Set.of(role));
        account.setPassword(passwordEncoder.encode(account.getPassword()));

        Account savedAccount = accountsRepository.saveAndFlush(account);
        rolesRepository.saveAll(account.getRoles());

        role.getAccounts().add(savedAccount);

        return savedAccount;
    }

    public List<Account> findAll() {
        return accountsRepository.findAll();
    }

    public Account findById(int id) {
        return accountsRepository.findById(id)
                .orElseThrow(() -> new UsernameNotFoundException(String.format("User with id: %s not found", id)));
    }

    @Transactional
    public Account update(int id, Account accountWithNewInfo) {
        Account accountFromDatabase = accountsRepository.findById(id)
                .orElseThrow(() -> new UsernameNotFoundException(String.format("User with id: %s not found", id)));

        accountFromDatabase.setName(accountWithNewInfo.getName());
        accountFromDatabase.setSurname(accountWithNewInfo.getSurname());
        accountFromDatabase.setPatronymic(accountWithNewInfo.getPatronymic());

        return accountsRepository.saveAndFlush(accountFromDatabase);
    }

    @Transactional
    public void delete(int id) {
        accountsRepository.deleteById(id);
    }

    public Set<Contact> findContactsForAccount(int id) {
        Account account = accountsRepository.findById(id)
                .orElseThrow(() -> new UsernameNotFoundException(String.format("User with id: %s not found", id)));

        return contactsRepository.findContactsByCreator(account);
    }

    public Account findByUsername(String username) {
        return accountsRepository.getByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(String.format("User with username: %s not found", username)));
    }
}
