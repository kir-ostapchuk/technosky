package com.ostapchuk.technosky.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class OperationDto {
    private Long senderId;
    private Long receiverId;
    private BigDecimal amount;
}
