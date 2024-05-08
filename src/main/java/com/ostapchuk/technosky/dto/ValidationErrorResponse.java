package com.ostapchuk.technosky.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class ValidationErrorResponse {
    private int status;
    private List<FieldErrorDto> fieldErrors;

    public void addFieldError(final String path, final String message) {
        final FieldErrorDto error = new FieldErrorDto(path, message);
        fieldErrors.add(error);
    }
}
