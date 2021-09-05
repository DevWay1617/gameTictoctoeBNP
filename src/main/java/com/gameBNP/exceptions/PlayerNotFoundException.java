package com.gameBNP.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class PlayerNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public PlayerNotFoundException() {
        super("Player not found");
    }

    public PlayerNotFoundException(String message) {
        super(message);
    }
}
