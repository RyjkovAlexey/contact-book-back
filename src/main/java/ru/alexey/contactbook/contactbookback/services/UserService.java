package ru.alexey.contactbook.contactbookback.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.alexey.contactbook.contactbookback.exceptions.NotFoundUserException;
import ru.alexey.contactbook.contactbookback.exceptions.UserErrorLoginException;
import ru.alexey.contactbook.contactbookback.models.user.Role;
import ru.alexey.contactbook.contactbookback.models.user.User;
import ru.alexey.contactbook.contactbookback.repositories.UsersRepository;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class UserService {

    private final UsersRepository usersRepository;

    @Autowired
    public UserService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    public List<User> findAll(boolean showDeleted) {
        return usersRepository.findUsersByDeletedNot(showDeleted);
    }

    public User findById(int id) {
        Optional<User> optionalUser = usersRepository.findById(id);

        optionalUser.orElseThrow(() -> new NotFoundUserException(id));

        return optionalUser.get();
    }

    @Transactional(readOnly = false)
    public User save(User user) {
        user.setRole(Role.USER);
        return usersRepository.saveAndFlush(user);
    }

    @Transactional(readOnly = false)
    public User update(int id, User updatedUser) {
        Optional<User> optionalUser = usersRepository.findById(id);

        optionalUser.ifPresent((user) -> {
            user.setName(updatedUser.getName());
            user.setSurname(updatedUser.getSurname());
            user.setPatronymic(updatedUser.getPatronymic());
        });

        optionalUser.orElseThrow(() -> new NotFoundUserException(id));

        return usersRepository.saveAndFlush(optionalUser.get());
    }

    public void remove(int id) {
        Optional<User> optionalUser = usersRepository.findById(id);

        optionalUser.ifPresent(user -> user.setDeleted(true));
        optionalUser.orElseThrow(() -> new NotFoundUserException(id));

        usersRepository.save(optionalUser.get());
    }

    public User login(User user) {
        Optional<User> optionalUser = usersRepository.findUserByLogin(user.getLogin());

        optionalUser.orElseThrow(() -> new NotFoundUserException(user.getName()));

        if (optionalUser.get().getPassword().equals(user.getPassword())) {
            return optionalUser.get();
        }

        throw new UserErrorLoginException("Invalid password");
    }
}
