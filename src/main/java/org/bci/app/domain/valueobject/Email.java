package org.bci.app.domain.valueobject;

import lombok.Getter;
import org.bci.app.domain.exceptions.ValidatorException;
import org.springframework.http.HttpStatus;

import java.util.regex.Pattern;

@Getter
public class Email {
    private final String value;

    public Email(String value){
        if(!validEmail(value)) throw new ValidatorException("Correo no valido", HttpStatus.CONFLICT);
        this.value = value;
    }

    private boolean validEmail(String value) {
        return Pattern.matches(value,"");
    }


}
