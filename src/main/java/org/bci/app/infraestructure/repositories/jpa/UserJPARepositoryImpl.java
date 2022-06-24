package org.bci.app.infraestructure.repositories.jpa;

import lombok.AllArgsConstructor;
import org.bci.app.domain.dto.UserCreationDTO;
import org.bci.app.domain.dto.UserDTO;
import org.bci.app.domain.entities.User;
import org.bci.app.domain.mappers.UserMapper;
import org.bci.app.infraestructure.repositories.IUserService;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserJPARepositoryImpl implements IUserService {

    private final IUserJPARepository userJPARepository;

    @Override
    public UserDTO createUser(UserCreationDTO user) {
        User userEntity =  userJPARepository.save(UserMapper.mapNewUser(user));
        return UserMapper.from(userEntity);
    }

    @Override
    public User getUserByEmail(String email) {
        return userJPARepository.findByEmail(email);
    }


}
