package ru.alexey.contactbook.contactbookback.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.alexey.contactbook.contactbookback.model.user.Account;
import ru.alexey.contactbook.contactbookback.repository.AccountsRepository;

@Service
@Transactional
public class UserDetailsServiceImpl implements UserDetailsService {
    private final AccountsRepository accountsRepository;

    @Autowired
    public UserDetailsServiceImpl(AccountsRepository accountsRepository) {
        this.accountsRepository = accountsRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        Account account = accountsRepository.findAccountByUsername(login)
                .orElseThrow(() -> new UsernameNotFoundException(login));

        return build(account);
    }

    private User build(Account account) {
        return new User(account.getUsername(), account.getPassword(), account.getRoles());
    }
}
