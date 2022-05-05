package org.bci.app.domain.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(value = {UserException.class})
    public ResponseEntity<Object> handleApiRequestException(UserException exception){
        ApiException apiException = new ApiException(exception.getMessage());
        return new ResponseEntity<>(apiException,exception.getStatus());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleValidationExceptions(MethodArgumentNotValidException ex) {
        ApiException apiException = new ApiException(
                String.format("campo %s : %s",
                          ((FieldError) ex.getBindingResult().getAllErrors().get(0)).getField()
                        ,ex.getBindingResult().getAllErrors().get(0).getDefaultMessage()));

        return new ResponseEntity<>(apiException,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ValidatorException.class)
    public ResponseEntity<Object> handleValidationExceptions(ValidatorException ex) {
        ApiException apiException = new ApiException(ex.getMessage());
        return new ResponseEntity<>(apiException,ex.getStatus());
    }
}
