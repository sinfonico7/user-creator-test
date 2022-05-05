package org.bci.app.domain.exceptions;

import lombok.Data;
import org.springframework.http.HttpStatus;
@Data
public class ValidatorException extends RuntimeException{

    private HttpStatus status;

    public ValidatorException(String message, HttpStatus status){
        super(message);
        this.status = status;
    }
}
