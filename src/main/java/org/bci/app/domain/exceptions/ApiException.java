package org.bci.app.domain.exceptions;

import lombok.Data;

@Data
public class ApiException {
    private final String message;

    public ApiException(String message) {
        this.message = message;
    }
}
