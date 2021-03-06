package org.bci.app.infraestructure.repositories;

import org.bci.app.domain.dto.UserCreationDTO;
import org.bci.app.domain.dto.UserDTO;
import org.bci.app.domain.entities.User;

public interface IUserService {
    UserDTO createUser(UserCreationDTO user);

    User getUserByEmail(String email);
}
