package org.bci.app.infraestructure.google;

import org.bci.app.domain.dto.UserCreationDTO;
import org.bci.app.domain.dto.UserDTO;
import org.bci.app.domain.entities.User;
import org.bci.app.infraestructure.IUserRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
@Qualifier("Google")
public class StoreGooleDoc implements IUserRepository {


    @Override
    public UserDTO createUser(UserCreationDTO user) {
        return null;
    }

    @Override
    public User getUserByEmail(String email) {
        System.out.println("Obteniendo informacion de google doc");
        return null;
    }

    @Override
    public User save(User newUser) {
        return null;
    }
}
