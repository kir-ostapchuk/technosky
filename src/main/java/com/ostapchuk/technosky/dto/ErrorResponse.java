package com.ostapchuk.technosky.dto;

import lombok.Value;

@Value
public class ErrorResponse {

    String message;
    int status;
}
