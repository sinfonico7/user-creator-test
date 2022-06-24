package org.bci.app.usecases;

import org.bci.app.domain.adapters.IUserRepository;
import org.bci.app.domain.dto.PhoneDTO;
import org.bci.app.domain.dto.UserCreationDTO;
import org.bci.app.domain.dto.UserDTO;
import org.bci.app.domain.entities.Phone;
import org.bci.app.domain.entities.User;
import org.bci.app.domain.exceptions.ValidatorException;
import org.bci.app.domain.utils.TokenUtils;
import org.bci.app.infraestructure.repositories.jpa.IUserJPARepository;
import org.bci.app.infraestructure.repositories.jpa.UserJPARepositoryImpl;
import org.bci.app.infraestructure.web.controllers.UserController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.core.env.Environment;
import java.util.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class UserUseCaseTest {
    private Environment environment;
    private final Date now = new Date();
    private final String ID = "671bc9fd-7d61-4149-a369-9539163dd5f6";
    UserUseCase userUseCase;

    UserJPARepositoryImpl jpaRepository;
    TokenUtils tokenUtils;
    User user;
    User userWithoutPhones;
    UserCreationDTO userCreationDTO;

    @BeforeEach
    void setUp() {

        user = buildMockUserWithPhones();
        userWithoutPhones = buildMockUserWithoutPhones();
        userCreationDTO = buildUserCreatio();
    }

    private UserCreationDTO buildUserCreatio() {
        return UserCreationDTO.builder()
                .email("juan@rodriguez.org")
                .name("Juan Rodriguez")
                .password("hunter2")
                .phones(Arrays.asList(PhoneDTO.builder().number("1234567").citycode("1").countrycode("57").build()))
                .build();
    }

    private User buildMockUserWithPhones() {

        List<Phone> phones = Arrays.asList(Phone.builder().cityCode("1").countryCode("57").number("1234567").build());
        return User.builder()
                .email("juan@rodriguez.org")
                .password("hunter2")
                .created(now)
                .lastLogin(now)
                .modified(now)
                .id(ID)
                .isActive(true)
                .token("el.super.token")
                .phones(phones)
                .build();
    }

    private User buildMockUserWithoutPhones() {

        return User.builder()
                .email("juan@rodriguez.org")
                .password("hunter2")
                .created(now)
                .lastLogin(now)
                .modified(now)
                .id(ID)
                .isActive(true)
                .token("el.super.token")
                .build();
    }
/*
    @Test
    void createUser() {
        when(jpaRepository.findByEmail("juan@rofriguez.cl")).thenReturn(null);
        when(tokenUtils.generateTokenFromUser(any(User.class),anyString())).thenReturn("el.super.token");
        when(jpaRepository.save(any(User.class))).thenReturn(user);
        UserDTO response = UserDTO.builder().created(now).id(ID).isActive(true).lastLogin(now).token("el.super.token").modifed(now).build();
        assertEquals(response,userUseCase.createUser(userCreationDTO));
    }

    //When the password doesn't match should throw Validation exception
    @Test
    void thePasswordMustMachWithRegex() {
        UserController userController = new UserController(environment,userUseCase);
        when(environment.getProperty("validator.password.regex")).thenReturn("[a-z]*");
        String badPassword = "lettersandnumbers293497328749";
        assertThrows(ValidatorException.class, () -> userController.createUser(UserCreationDTO.builder().password(badPassword).build()));
    }

    @Test
    void whenCreteAUserWithoutPhonesItShouldNotFail(){
        when(jpaRepository.findByEmail("juan@rofriguez.cl")).thenReturn(null);
        when(tokenUtils.generateTokenFromUser(any(User.class),anyString())).thenReturn("el.super.token");
        when(jpaRepository.save(any(User.class))).thenReturn(userWithoutPhones);
        UserDTO response = UserDTO.builder().created(now).id(ID).isActive(true).lastLogin(now).token("el.super.token").modifed(now).build();
        assertEquals(response,userUseCase.createUser(userCreationDTO));
    }
    */

}