package ru.alexey.contactbook.contactbookback.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.alexey.contactbook.contactbookback.models.user.User;

@Repository
public interface UsersRepository extends JpaRepository<User, Integer> {
}
