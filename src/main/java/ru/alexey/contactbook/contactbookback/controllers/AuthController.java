package ru.alexey.contactbook.contactbookback.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.alexey.contactbook.contactbookback.dto.contactdto.LoginDTO;
import ru.alexey.contactbook.contactbookback.dto.userdto.UserDTO;
import ru.alexey.contactbook.contactbookback.exceptions.BaseUserException;
import ru.alexey.contactbook.contactbookback.models.user.User;
import ru.alexey.contactbook.contactbookback.services.UserService;
import ru.alexey.contactbook.contactbookback.utils.ErrorResponse;
import ru.alexey.contactbook.contactbookback.utils.ModelUtils;

import java.net.http.HttpResponse;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final UserService userService;

    @Autowired
    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login" )
    public ResponseEntity<UserDTO> login(
            @RequestBody LoginDTO loginDTO
    ) {
        User user = ModelUtils.convertToModel(loginDTO, User.class);

        return new ResponseEntity<UserDTO>(
                ModelUtils.convertToDTO(userService.login(user), UserDTO.class),
                HttpStatus.ACCEPTED
        );
    }

    @ExceptionHandler
    private ResponseEntity<ErrorResponse> handleException(BaseUserException exp) {
        ErrorResponse response = new ErrorResponse(
                exp.getMessage(),
                System.currentTimeMillis()
        );
        return new ResponseEntity<>(response, exp.httpStatus());
    }
}
