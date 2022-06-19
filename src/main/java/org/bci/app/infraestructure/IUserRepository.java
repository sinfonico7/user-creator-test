package org.bci.app.infraestructure;

import org.bci.app.domain.dto.UserCreationDTO;
import org.bci.app.domain.dto.UserDTO;
import org.bci.app.domain.entities.User;

public interface IUserRepository {

    UserDTO createUser(UserCreationDTO user);

    User getUserByEmail(String email);

    User save(User newUser);

}
