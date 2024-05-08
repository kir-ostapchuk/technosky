package com.ostapchuk.technosky.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TransferResponse {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String errorCode;

    private String operationStatus;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String message;
}
