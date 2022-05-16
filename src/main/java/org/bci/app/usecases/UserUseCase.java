package org.bci.app.usecases;

import lombok.RequiredArgsConstructor;
import org.bci.app.domain.dto.UserCreationDTO;
import org.bci.app.domain.dto.UserDTO;
import org.bci.app.domain.entities.Phone;
import org.bci.app.domain.entities.User;
import org.bci.app.domain.exceptions.UserException;
import org.bci.app.domain.mappers.UserMapper;
import org.bci.app.domain.utils.TokenUtils;
import org.bci.app.infraestructure.repositories.IUserRepository;
import org.bci.app.infraestructure.repositories.jpa.IUserJPARepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import static org.bci.app.domain.mappers.UserMapper.to;

@Service
@RequiredArgsConstructor
public class UserUseCase implements IUserRepository {


    private  final IUserJPARepository repository;

    private final TokenUtils tokenService;

    @Override
    public UserDTO createUser(UserCreationDTO user) throws UserException {
        Optional<User> optUser = Optional.ofNullable(getUserByEmail(user.getEmail()));
        if(optUser.isPresent()) throw new UserException("El correo ya registrado", HttpStatus.CONFLICT);
        User newUser = mapNewUser(user);
        return  UserMapper.from(repository.save(newUser)) ;
    }

    @Override
    public User getUserByEmail(String email) {
        return repository.findByEmail(email);
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
