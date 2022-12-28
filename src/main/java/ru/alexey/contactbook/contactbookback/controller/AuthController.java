package ru.alexey.contactbook.contactbookback.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import ru.alexey.contactbook.contactbookback.dto.AccountDTO;
import ru.alexey.contactbook.contactbookback.model.user.Account;
import ru.alexey.contactbook.contactbookback.request.AuthenticationRequest;
import ru.alexey.contactbook.contactbookback.request.SignUpRequest;
import ru.alexey.contactbook.contactbookback.response.AuthenticationResponse;
import ru.alexey.contactbook.contactbookback.response.SignUpResponse;
import ru.alexey.contactbook.contactbookback.service.AccountsService;
import ru.alexey.contactbook.contactbookback.service.JwtTokenService;
import ru.alexey.contactbook.contactbookback.service.UserDetailsServiceImpl;

import javax.validation.Valid;

@RestController
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final UserDetailsServiceImpl userDetailsService;

    private final JwtTokenService jwtTokenService;

    private final AccountsService accountsService;

    private final ModelMapper modelMapper;

    @Autowired
    public AuthController(AuthenticationManager authenticationManager, UserDetailsServiceImpl userDetailsService, JwtTokenService jwtTokenService, AccountsService accountsService, ModelMapper modelMapper) {
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
        this.jwtTokenService = jwtTokenService;
        this.accountsService = accountsService;
        this.modelMapper = modelMapper;
    }

    @PostMapping("/authenticate")
    public AuthenticationResponse authenticate(
            @RequestBody @Valid final AuthenticationRequest authenticationRequest
    ) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    authenticationRequest.getUsername(), authenticationRequest.getPassword()));
        } catch (final BadCredentialsException ex) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }

        final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
        final AuthenticationResponse authenticationResponse = new AuthenticationResponse();
        final Account account = accountsService.findByUsername(userDetails.getUsername());

        authenticationResponse.setAccountDTO(modelMapper.map(account, AccountDTO.class));
        authenticationResponse.setAccessToken(jwtTokenService.generateToken(userDetails));
        return authenticationResponse;
    }

    @PostMapping("/singUp")
    public SignUpResponse singUp(
            @RequestBody @Valid final SignUpRequest signUpRequest
    ) {
        Account account = modelMapper.map(signUpRequest, Account.class);

        Account savedAccount = accountsService.save(account);

        return modelMapper.map(savedAccount, SignUpResponse.class);
    }
}
