package ru.alexey.contactbook.contactbookback.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.alexey.contactbook.contactbookback.model.user.Role;

@Repository
public interface RolesRepository extends JpaRepository<Role, Integer> {
    public Role getRoleByName(String name);
}
