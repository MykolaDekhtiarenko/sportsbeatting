package com.epam.training.sportsbeatting.exeptionhandling.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ValidationErrorResponse extends AbstractErrorResponse {
    private List<ErrorDetails> errors;

    public ValidationErrorResponse(ErrorResponseType type, List<ErrorDetails> errors) {
        super(type);
        this.errors = errors;
    }

    @Getter
    @Setter
    public static class ErrorDetails {
        private String field;
        private String message;
    }
}
