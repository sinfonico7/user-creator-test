package org.bci.app.usecases;

import lombok.RequiredArgsConstructor;
import org.bci.app.domain.dto.UserCreationDTO;
import org.bci.app.domain.dto.UserDTO;
import org.bci.app.domain.entities.Phone;
import org.bci.app.domain.entities.User;
import org.bci.app.domain.exceptions.UserException;
import org.bci.app.domain.mappers.UserMapper;
import org.bci.app.domain.utils.TokenUtils;
import org.bci.app.infraestructure.IUserRepository;
import org.springframework.http.HttpStatus;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import static org.bci.app.domain.mappers.UserMapper.to;

@RequiredArgsConstructor
public class UserUseCase {

    private final IUserRepository repository;
    private final TokenUtils tokenService;

    public UserDTO createUser(UserCreationDTO user) throws UserException {
        Optional<User> optUser = Optional.ofNullable(getUserByEmail(user.getEmail()));
        if(optUser.isPresent()) throw new UserException("El correo ya registrado", HttpStatus.CONFLICT);
        User newUser = mapNewUser(user);
        return UserMapper.from(repository.save(newUser)) ;
    }

    public User getUserByEmail(String email) {
        System.out.println("llamando a la inferfaz...");
        return repository.getUserByEmail(email);
    }

    private User mapNewUser(UserCreationDTO userCreationDTO){
        User newUser = UserMapper.from(userCreationDTO);
        Date now = new Date();
        String id = String.valueOf(UUID.randomUUID());
        newUser.setCreated(now);
        newUser.setLastLogin(now);
        newUser.setModified(now);
        newUser.setId(id);
        newUser.setIsActive(true);
        if(userCreationDTO.hasPhones()){
            List<Phone> phones = to(userCreationDTO.getPhones(),newUser);
            newUser.setPhones(phones);
        }
        String token = tokenService.generateTokenFromUser(newUser,id);
        newUser.setToken(token);

        return newUser;
    }
}
