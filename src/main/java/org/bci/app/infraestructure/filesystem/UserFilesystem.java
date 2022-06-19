package org.bci.app.infraestructure.filesystem;

import org.bci.app.domain.dto.UserCreationDTO;
import org.bci.app.domain.dto.UserDTO;
import org.bci.app.domain.entities.User;
import org.bci.app.infraestructure.IUserRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
@Qualifier("fileSystem")
public class UserFilesystem implements IUserRepository {

    @Override
    public UserDTO createUser(UserCreationDTO user) {
        // crear el archivo
        return null;
    }

    @Override
    public User getUserByEmail(String email) {
        //TODO: buscar el archivo
        System.out.println("Llamando al filesystem");
        return null;
    }

    @Override
    public User save(User newUser) {
        // grabar el archivo en disco
        return null;
    }
}
