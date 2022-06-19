package org.bci.app.infraestructure.repositories.jpa;

import lombok.AllArgsConstructor;
import org.bci.app.domain.dto.UserCreationDTO;
import org.bci.app.domain.dto.UserDTO;
import org.bci.app.domain.entities.User;
import org.bci.app.infraestructure.IUserRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@Qualifier("JPA")
@Service
@AllArgsConstructor
public class UserJPAImpl implements IUserRepository {

    private final IUserJPARepository iUserJPARepository;

    @Override
    public User getUserByEmail(String email) {
        System.out.println("Llamando a JPA");
        return iUserJPARepository.findByEmail(email);
    }

    @Override
    public User save(User newUser) {
        return iUserJPARepository.save(newUser);
    }

    @Override
    public UserDTO createUser(UserCreationDTO user) {
        //return iUserJPARepository.save(user); TODO: hacer mapping
        return null;
    }
}
