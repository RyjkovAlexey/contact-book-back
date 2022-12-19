package ru.alexey.contactbook.contactbookback.controllers;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.alexey.contactbook.contactbookback.dto.*;
import ru.alexey.contactbook.contactbookback.exceptions.BaseUserException;
import ru.alexey.contactbook.contactbookback.exceptions.UserNotCreatedException;
import ru.alexey.contactbook.contactbookback.exceptions.UserNotUpdatedException;
import ru.alexey.contactbook.contactbookback.models.user.User;
import ru.alexey.contactbook.contactbookback.services.UserService;
import ru.alexey.contactbook.contactbookback.utils.ErrorMessageUtils;
import ru.alexey.contactbook.contactbookback.utils.UserErrorResponse;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final ModelMapper modelMapper;
    private final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    public UserController(UserService userService, ModelMapper modelMapper) {
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @GetMapping()
    public ResponseEntity<List<UserDTO>> getAll(
            @RequestParam(name = "showDeleted", defaultValue = "false", required = false) boolean showDeleted
    ) {
        List<User> users = userService.findAll(showDeleted);

        List<UserDTO> userDTOS = users.stream().map((user) -> convertToDTO(user, UserDTO.class)).toList();

        return new ResponseEntity<>(userDTOS, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<NewUserDTO> create(
            @RequestBody @Valid RegisterUserDTO registerUserDTO,
            BindingResult bindingResult
    ) {
        User createdUser = convertToUser(registerUserDTO);

        if (bindingResult.hasErrors()) {
            String error = ErrorMessageUtils.buildErrorMessage(bindingResult.getFieldErrors());
            logger.error(error);
            throw new UserNotCreatedException(error);
        }

        NewUserDTO newUserDTO = convertToDTO(userService.save(createdUser), NewUserDTO.class);

        return new ResponseEntity<>(newUserDTO, HttpStatus.CREATED);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<UpdatedUserDTO> update(
            @RequestBody @Valid UpdateUserDTO updateUserDTO,
            BindingResult bindingResult,
            @PathVariable int id
    ) {
        User user = convertToUser(updateUserDTO);

        if (bindingResult.hasErrors()) {
            String error = ErrorMessageUtils.buildErrorMessage(bindingResult.getFieldErrors());
            logger.error(error);
            throw new UserNotUpdatedException(error);
        }

        UpdatedUserDTO updatedUser = convertToDTO(userService.update(id, user), UpdatedUserDTO.class);

        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(
            @PathVariable("id") int id
    ) {
        userService.remove(id);
        return ResponseEntity.ok("deleted");
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getById(
            @PathVariable int id
    ) {
        User user = userService.findById(id);

        return new ResponseEntity<>(convertToDTO(user, UserDTO.class), HttpStatus.OK);
    }

    @ExceptionHandler
    private ResponseEntity<UserErrorResponse> handleException(BaseUserException exp) {
        UserErrorResponse response = new UserErrorResponse(
                exp.getMessage(),
                System.currentTimeMillis()
        );
        return new ResponseEntity<>(response, exp.httpStatus());
    }

    private <T> T convertToDTO(User user, Class<T> tClass) {
        return modelMapper.map(user, tClass);
    }

    private <T> User convertToUser(T DTO) {
        return modelMapper.map(DTO, User.class);
    }
}
