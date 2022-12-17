package ru.alexey.contactbook.contactbookback.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.alexey.contactbook.contactbookback.models.user.User;
import ru.alexey.contactbook.contactbookback.services.UserService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    public List<User> getAllUsers() {
        return userService.findAll();
    }

    @GetMapping("/:id")
    public User getUser(
            @RequestParam("id") int id
    ) {
        Optional<User> user = userService.findById(id);
        if (user.isEmpty()) {
            throw new RuntimeException("Error");
        }

        return user.get();
    }

    @PostMapping()
    public User newUser(
            @RequestBody User user
    ) {
        return userService.save(user);
    }

    @PatchMapping("/{id}")
    public String updateUser(
            @PathVariable String id) {
        System.out.println("================");
        System.out.println(id + " ");
        return "awsdasd";
    }
}
