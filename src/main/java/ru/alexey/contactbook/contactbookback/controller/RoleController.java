package ru.alexey.contactbook.contactbookback.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.alexey.contactbook.contactbookback.dto.AccountDTO;
import ru.alexey.contactbook.contactbookback.dto.RoleDTO;
import ru.alexey.contactbook.contactbookback.service.RolesService;

import java.util.Set;
import java.util.stream.Collectors;

@RestController("/roles")
public class RoleController {
    private final RolesService rolesService;
    private final ModelMapper modelMapper;

    @Autowired
    public RoleController(RolesService rolesService, ModelMapper modelMapper) {
        this.rolesService = rolesService;
        this.modelMapper = modelMapper;
    }

    @GetMapping()
    public Set<RoleDTO> getAllRoles() {
        return rolesService.findAll().stream()
                .map(role -> modelMapper.map(role, RoleDTO.class))
                .collect(Collectors.toSet());
    }

    @GetMapping("/{id}")
    public Set<AccountDTO> getAccountsWithRole(@PathVariable int id) {
        return rolesService.findAllWithRole(id).stream()
                .map(account -> modelMapper.map(account, AccountDTO.class))
                .collect(Collectors.toSet());
    }

    @PostMapping("/{id}/addAccount")
    public void addAccount(@PathVariable int id, @RequestParam int accountId){
        rolesService.addAccount(id, accountId);
    }

    @DeleteMapping("/{id}/addAccount")
    public void removeAccount(@PathVariable int id, @RequestParam int accountId){
        rolesService.removeAccount(id, accountId);
    }
}
