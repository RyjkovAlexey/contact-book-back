package ru.alexey.contactbook.contactbookback.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.alexey.contactbook.contactbookback.model.user.Department;

@Repository
public interface DepartmentsRepository extends JpaRepository<Department, Integer> {
}
