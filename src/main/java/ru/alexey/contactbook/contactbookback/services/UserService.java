package ru.alexey.contactbook.contactbookback.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.alexey.contactbook.contactbookback.models.user.Role;
import ru.alexey.contactbook.contactbookback.models.user.User;
import ru.alexey.contactbook.contactbookback.models.user.UserInfo;
import ru.alexey.contactbook.contactbookback.repositories.UsersInfoRepository;
import ru.alexey.contactbook.contactbookback.repositories.UsersRepository;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class UserService {

    private final UsersRepository usersRepository;
    private final UsersInfoRepository usersInfoRepository;

    @Autowired
    public UserService(UsersRepository usersRepository, UsersInfoRepository userInfoRepository) {
        this.usersRepository = usersRepository;
        this.usersInfoRepository = userInfoRepository;
    }

    public List<User> findAll() {
        return usersRepository.findAll();
    }

    public Optional<User> findById(int id) {
        return usersRepository.findById(id);
    }

    @Transactional(readOnly = false)
    public User save(User user) {
        user.setRole(Role.USER);
        UserInfo userInfo = user.getInfo();
        user.setInfo(null);
        User savedUser = usersRepository.saveAndFlush(user);
        userInfo.setUser(savedUser);
        usersInfoRepository.save(userInfo);
        savedUser.setInfo(userInfo);
        return savedUser;
    }
}
