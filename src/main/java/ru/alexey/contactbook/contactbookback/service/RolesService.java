package ru.alexey.contactbook.contactbookback.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.alexey.contactbook.contactbookback.dto.RoleDTO;
import ru.alexey.contactbook.contactbookback.model.user.Account;
import ru.alexey.contactbook.contactbookback.model.user.Role;
import ru.alexey.contactbook.contactbookback.repository.RolesRepository;

import java.util.List;
import java.util.Set;

@Service
@Transactional(readOnly = true)
public class RolesService {
    private final RolesRepository rolesRepository;

    @Autowired
    public RolesService(RolesRepository rolesRepository) {
        this.rolesRepository = rolesRepository;
    }

    public List<Role> findAll() {
        return rolesRepository.findAll();
    }

    public Set<Account> findAllWithRole(int id) {
        return rolesRepository.getById(id).getAccounts();
    }

    @Transactional
    public void addAccount(int roleId, int accountId) {
        Role role = rolesRepository.getById(roleId);

        Account account = new Account();
        account.setId(accountId);

        role.getAccounts().add(account);

        rolesRepository.save(role);
    }

    @Transactional
    public void removeAccount(int roleId, int accountId) {
        Role role = rolesRepository.getById(roleId);

        role.getAccounts().removeIf(account -> account.getId()==accountId);

        rolesRepository.save(role);
    }
}
