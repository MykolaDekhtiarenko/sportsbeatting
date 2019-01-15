package com.epam.training.sportsbeatting.exeptionhandling.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class AbstractErrorResponse {
    ErrorResponseType type;

    public AbstractErrorResponse(ErrorResponseType type) {
        this.type = type;
    }
}
