package org.bci.app.domain.mappers;

import org.bci.app.domain.dto.PhoneDTO;
import org.bci.app.domain.dto.UserCreationDTO;
import org.bci.app.domain.dto.UserDTO;
import org.bci.app.domain.entities.Phone;
import org.bci.app.domain.entities.User;

import java.util.List;
import java.util.stream.Collectors;

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

    public static List<Phone> to(List<PhoneDTO> phones , User user){
        return phones.stream().map(phoneDTO -> new Phone(phoneDTO.getCitycode(), phoneDTO.getCountrycode(), phoneDTO.getNumber(), user)).collect(Collectors.toList());
    }

}
