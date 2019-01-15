package com.epam.training.sportsbeatting.exeptionhandling.handler;

import com.epam.training.sportsbeatting.exeptionhandling.dto.ErrorResponseType;
import com.epam.training.sportsbeatting.exeptionhandling.dto.ValidationErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class CustomValidationExceptionHandler {

    @ResponseBody
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ValidationErrorResponse handleException(MethodArgumentNotValidException ex) {

        List<FieldError> errors = ex.getBindingResult().getFieldErrors();

        List<ValidationErrorResponse.ErrorDetails> errorDetails = new ArrayList<>();
        for (FieldError fieldError : errors) {
            ValidationErrorResponse.ErrorDetails error = new ValidationErrorResponse.ErrorDetails();
            error.setField(fieldError.getField());
            error.setMessage(fieldError.getDefaultMessage());
            errorDetails.add(error);
        }

        return new ValidationErrorResponse(ErrorResponseType.VALIDATION, errorDetails);
    }

}