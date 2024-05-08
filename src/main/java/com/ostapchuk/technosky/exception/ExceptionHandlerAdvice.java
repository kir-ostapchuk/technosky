package com.ostapchuk.technosky.exception;

import com.ostapchuk.technosky.dto.ErrorResponse;
import com.ostapchuk.technosky.dto.ValidationErrorResponse;
import org.hibernate.StaleObjectStateException;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@RestControllerAdvice
public class ExceptionHandlerAdvice {

    @ResponseStatus(BAD_REQUEST)
    @ExceptionHandler({EntityNotFoundException.class, AccountBalanceException.class})
    public ErrorResponse entityNotFoundException(final RuntimeException ex) {
        return new ErrorResponse(ex.getMessage(), BAD_REQUEST.value());
    }

    @ResponseStatus(BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ValidationErrorResponse methodArgumentNotValidException(final MethodArgumentNotValidException ex) {
        final BindingResult result = ex.getBindingResult();
        final List<FieldError> fieldErrors = result.getFieldErrors();
        return processFieldErrors(fieldErrors);
    }

    private ValidationErrorResponse processFieldErrors(final List<FieldError> fieldErrors) {
        final ValidationErrorResponse validationErrorResponseDto =
              new ValidationErrorResponse(BAD_REQUEST.value(), new ArrayList<>());
        for (final FieldError fieldError : fieldErrors) {
            validationErrorResponseDto.addFieldError(fieldError.getField(), fieldError.getDefaultMessage());
        }
        return validationErrorResponseDto;
    }
}
