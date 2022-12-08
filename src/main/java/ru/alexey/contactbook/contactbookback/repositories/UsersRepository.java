package ru.alexey.contactbook.contactbookback.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.alexey.contactbook.contactbookback.models.user.User;

public interface UsersRepository extends JpaRepository<User, Integer> {
}
