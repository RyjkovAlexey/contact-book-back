package ru.alexey.contactbook.contactbookback.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.alexey.contactbook.contactbookback.model.user.Account;

import java.util.Optional;

@Repository
public interface AccountsRepository extends JpaRepository<Account, Integer> {
    Optional<Account> findAccountByUsername(String username);
}
