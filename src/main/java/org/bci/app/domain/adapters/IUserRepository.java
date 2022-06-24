package org.bci.app.domain.adapters;

import org.bci.app.domain.dto.UserCreationDTO;
import org.bci.app.domain.dto.UserDTO;
import org.bci.app.domain.valueobject.Email;

public interface IUserRepository {
    public UserDTO createUser(UserCreationDTO userCreationDTO);
    public UserDTO getUserByEmail(Email email);
}
