package org.bci.app.domain.mappers;

import lombok.AllArgsConstructor;
import org.bci.app.domain.dto.PhoneDTO;
import org.bci.app.domain.dto.UserCreationDTO;
import org.bci.app.domain.dto.UserDTO;
import org.bci.app.domain.entities.Phone;
import org.bci.app.domain.entities.User;
import org.bci.app.domain.utils.TokenUtils;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@AllArgsConstructor
public class UserMapper {
    public static User from(UserCreationDTO userDTO) {
        User user = User.builder().email(userDTO.getEmail())
                .name(userDTO.getName())
                .password(userDTO.getPassword())
                .build();
        return user;

    }

    public static UserDTO from(User user) {
        return UserDTO.builder()
                .created(user.getCreated())
                .id(user.getId())
                .isActive(user.getIsActive())
                .lastLogin(user.getLastLogin())
                .modifed(user.getModified())
                .token(user.getToken())
                .build();
    }
    public static User mapNewUser(UserCreationDTO userCreationDTO){
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
        String token = TokenUtils.generateTokenFromUser(newUser,id);
        newUser.setToken(token);

        return newUser;
    }

    public static List<Phone> to(List<PhoneDTO> phones , User user){
        return phones.stream().map(phoneDTO -> new Phone(phoneDTO.getCitycode(), phoneDTO.getCountrycode(), phoneDTO.getNumber(), user)).collect(Collectors.toList());
    }

}
