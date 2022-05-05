package org.bci.app.domain.exceptions;

import lombok.Data;
import org.springframework.http.HttpStatus;
@Data
public class UserException extends RuntimeException{

    private HttpStatus status;

    public UserException(String message,HttpStatus status){
        super(message);
        this.status = status;
    }
}
