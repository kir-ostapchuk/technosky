package com.ostapchuk.technosky.dto;

import lombok.Builder;
import lombok.Value;

import java.math.BigDecimal;

@Value
@Builder
public class OperationDto {

    Long id;
    Long senderId;
    Long receiverId;
    BigDecimal amount;
}
