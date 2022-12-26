package ru.alexey.contactbook.contactbookback.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.alexey.contactbook.contactbookback.model.user.Account;
import ru.alexey.contactbook.contactbookback.model.user.Role;
import ru.alexey.contactbook.contactbookback.repository.AccountsRepository;
import ru.alexey.contactbook.contactbookback.repository.RolesRepository;

import java.util.Set;

@Service
@Transactional(readOnly = true)
public class AccountsService {
    private final AccountsRepository accountsRepository;
    private final RolesRepository rolesRepository;

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AccountsService(AccountsRepository accountsRepository, RolesRepository rolesRepository, PasswordEncoder passwordEncoder) {
        this.accountsRepository = accountsRepository;
        this.rolesRepository = rolesRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public Account save(Account account) {
        Role role = rolesRepository.getRoleByName("ROLE_USER");

        account.setRoles(Set.of(role));
        account.setPassword(passwordEncoder.encode(account.getPassword()));

        Account savedAccount = accountsRepository.saveAndFlush(account);

        role.getAccounts().add(savedAccount);

        return savedAccount;
    }
}
