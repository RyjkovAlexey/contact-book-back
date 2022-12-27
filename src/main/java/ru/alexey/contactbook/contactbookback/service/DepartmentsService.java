package ru.alexey.contactbook.contactbookback.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.alexey.contactbook.contactbookback.dto.AccountDTO;
import ru.alexey.contactbook.contactbookback.exception.AccountNotFoundException;
import ru.alexey.contactbook.contactbookback.exception.DepartmentNotFoundException;
import ru.alexey.contactbook.contactbookback.model.user.Account;
import ru.alexey.contactbook.contactbookback.model.user.Department;
import ru.alexey.contactbook.contactbookback.repository.AccountsRepository;
import ru.alexey.contactbook.contactbookback.repository.DepartmentsRepository;

import java.util.HashSet;
import java.util.Set;

@Service
@Transactional(readOnly = true)
public class DepartmentsService {
    private final DepartmentsRepository departmentsRepository;
    private final AccountsRepository accountsRepository;

    @Autowired
    public DepartmentsService(DepartmentsRepository departmentsRepository, AccountsRepository accountsRepository) {
        this.departmentsRepository = departmentsRepository;
        this.accountsRepository = accountsRepository;
    }

    public Set<Department> findAll() {
        return new HashSet<>(departmentsRepository.findAll());
    }

    public Department findById(int id) {
        return departmentsRepository.findById(id)
                .orElseThrow(() -> new DepartmentNotFoundException(String.format("Not found department with id %s", id)));
    }

    @Transactional
    public Department save(Department department) {
        return departmentsRepository.saveAndFlush(department);
    }

    @Transactional
    public Department update(int id, Department department) {
        Department departmentFromDatabase = departmentsRepository.findById(id)
                .orElseThrow(() -> new DepartmentNotFoundException(String.format("Not found department with id %s", id)));

        departmentFromDatabase.setName(department.getName());

        return departmentsRepository.saveAndFlush(departmentFromDatabase);
    }

    @Transactional
    public void deleteById(int id) {
        departmentsRepository.deleteById(id);
    }

    @Transactional
    public void addAccount(int departmentId, int accountId) {
        Department department = departmentsRepository.findById(departmentId)
                .orElseThrow(() -> new DepartmentNotFoundException(String.format("Not found department with id %s", departmentId)));

        Account account = accountsRepository.findById(accountId)
                .orElseThrow(() -> new AccountNotFoundException(String.format("Not found account with id %s", accountId)));

        department.getAccounts().add(account);
        account.setDepartment(department);

        departmentsRepository.save(department);
        accountsRepository.save(account);
    }

    @Transactional
    public void removeAccount(int departmentId, int accountId) {
        Department department = departmentsRepository.findById(departmentId)
                .orElseThrow(() -> new DepartmentNotFoundException(String.format("Not found department with id %s", departmentId)));

        Account account = accountsRepository.findById(accountId)
                .orElseThrow(() -> new AccountNotFoundException(String.format("Not found account with id %s", accountId)));

        department.getAccounts().removeIf(_account -> _account.getId() == accountId);
        account.setDepartment(null);
    }

    public Set<Account> getAllAccountsInDepartment(int id) {
        Department department = departmentsRepository.findById(id)
                .orElseThrow(() -> new DepartmentNotFoundException(String.format("Not found department with id %s", id)));

        return department.getAccounts();
    }
}
