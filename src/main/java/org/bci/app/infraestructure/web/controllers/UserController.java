package org.bci.app.infraestructure.web.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bci.app.domain.dto.UserCreationDTO;
import org.bci.app.domain.dto.UserDTO;
import org.bci.app.domain.exceptions.UserException;
import org.bci.app.domain.exceptions.ValidatorException;
import org.bci.app.infraestructure.repositories.IUserService;
import org.bci.app.usecases.UserUseCase;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.regex.Pattern;

@Slf4j
@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final Environment environment;

    private final IUserService userUseCase;

    @PostMapping(consumes = "application/json",produces = "application/json")
    public ResponseEntity<UserDTO> createUser(@Valid @RequestBody UserCreationDTO user){

        String regex = environment.getProperty("validator.password.regex");

        log.info("[CREATE USER] Creating a user (we hide the pass): {}",user.toString());

        if (!Pattern.matches(regex, user.getPassword())) throw new ValidatorException(String.format("Password doesn't match with regex (%s) ", regex), HttpStatus.BAD_REQUEST);

        return new ResponseEntity<>(userUseCase.createUser(user), HttpStatus.CREATED);
    }
}