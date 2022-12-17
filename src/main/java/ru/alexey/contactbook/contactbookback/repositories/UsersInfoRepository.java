package ru.alexey.contactbook.contactbookback.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.alexey.contactbook.contactbookback.models.user.UserInfo;

@Repository
public interface UsersInfoRepository extends JpaRepository<UserInfo, Integer> {
}
