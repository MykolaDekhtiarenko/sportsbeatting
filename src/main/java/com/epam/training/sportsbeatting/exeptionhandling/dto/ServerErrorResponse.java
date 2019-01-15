package com.epam.training.sportsbeatting.exeptionhandling.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ServerErrorResponse extends AbstractErrorResponse {
    String message;
    String cause;

    public ServerErrorResponse(ErrorResponseType type, String message, String cause) {
        super(type);
        this.message = message;
        this.cause = cause;
    }
}
