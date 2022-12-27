package ru.alexey.contactbook.contactbookback.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.alexey.contactbook.contactbookback.dto.AccountDTO;
import ru.alexey.contactbook.contactbookback.dto.DepartmentDTO;
import ru.alexey.contactbook.contactbookback.model.user.Account;
import ru.alexey.contactbook.contactbookback.model.user.Department;
import ru.alexey.contactbook.contactbookback.request.NewDepartmentRequest;
import ru.alexey.contactbook.contactbookback.request.UpdateDepartmentRequest;
import ru.alexey.contactbook.contactbookback.service.DepartmentsService;

import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/departments")
public class DepartmentController {
    private final DepartmentsService departmentsService;

    private final ModelMapper modelMapper;

    @Autowired
    public DepartmentController(DepartmentsService departmentsService, ModelMapper modelMapper) {
        this.departmentsService = departmentsService;
        this.modelMapper = modelMapper;
    }

    @GetMapping()
    public Set<DepartmentDTO> getAll() {
        return departmentsService.findAll().stream()
                .map(department -> modelMapper.map(department, DepartmentDTO.class))
                .collect(Collectors.toSet());
    }

    @GetMapping("/{id}")
    public DepartmentDTO findById(@PathVariable int id) {
        return modelMapper.map(departmentsService.findById(id), DepartmentDTO.class);
    }

    @PostMapping()
    public DepartmentDTO save(
            @RequestBody NewDepartmentRequest newDepartmentRequest
    ) {
        Department department = modelMapper.map(newDepartmentRequest, Department.class);

        Department savedDepartment = departmentsService.save(department);

        return modelMapper.map(savedDepartment, DepartmentDTO.class);
    }

    @PatchMapping("/{id}")
    public DepartmentDTO update(
            @PathVariable int id,
            @RequestBody UpdateDepartmentRequest updateDepartmentRequest
    ) {
        Department department = modelMapper.map(updateDepartmentRequest, Department.class);

        Department updatedDepartment = departmentsService.update(id, department);

        return modelMapper.map(updatedDepartment, DepartmentDTO.class);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        departmentsService.deleteById(id);
    }

    @PutMapping("/{id}/addAccount")
    public void addAccount(
            @PathVariable int id,
            @RequestParam int accountId
    ) {
        departmentsService.addAccount(id, accountId);
    }

    @PatchMapping("/{id}/removeAccount")
    public void removeUser(
            @PathVariable int id,
            @RequestParam int accountId
    ) {
        departmentsService.removeAccount(id, accountId);
    }

    @GetMapping("/{id}/accounts")
    public Set<AccountDTO> getAccountInDepartment(@PathVariable int id) {
        return departmentsService.getAllAccountsInDepartment(id).stream()
                .map(account -> modelMapper.map(account, AccountDTO.class))
                .collect(Collectors.toSet());
    }
}
