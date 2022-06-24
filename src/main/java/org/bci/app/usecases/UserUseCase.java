package org.bci.app.usecases;

import lombok.RequiredArgsConstructor;
import org.bci.app.domain.adapters.IUserRepository;
import org.bci.app.domain.dto.UserCreationDTO;
import org.bci.app.domain.dto.UserDTO;
import org.bci.app.domain.exceptions.UserException;
import org.bci.app.domain.valueobject.Email;
import org.springframework.http.HttpStatus;
import java.util.Optional;

@RequiredArgsConstructor
public class UserUseCase {

    private  final IUserRepository repository;

    public UserDTO createUser(UserCreationDTO userCreationDTO) throws UserException {
        Optional<UserDTO> optUser = Optional.ofNullable(getUserByEmail(new Email(userCreationDTO.getEmail())));
        if(optUser.isPresent()) throw new UserException("El correo ya registrado", HttpStatus.CONFLICT);
        return  repository.createUser(userCreationDTO);
    }

    public UserDTO getUserByEmail(Email email) {
        return repository.getUserByEmail(email);
    }




}
