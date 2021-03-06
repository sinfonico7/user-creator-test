package org.bci.app.domain.dto;

import java.util.List;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
@Builder
public class UserCreationDTO {

    @NotBlank
    private String name;
    @NotBlank
    @Pattern(regexp = "[a-z0-9]+@[a-z]+\\.[a-z]{2,3}")
    private String email;
    @NotBlank
    private String password;
    private List<PhoneDTO> phones;

    @Override
    public String toString() {
        return "UserCreationDTO{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", phones=" + phones +
                '}';
    }

    public boolean hasPhones(){
        return phones != null;
    }
}