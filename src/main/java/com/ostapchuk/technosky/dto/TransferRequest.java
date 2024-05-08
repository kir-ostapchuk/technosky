package com.ostapchuk.technosky.dto;

import com.ostapchuk.technosky.entity.Currency;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class TransferRequest {

    @NotNull
    @Min(0)
    private Long senderId;
    @NotNull
    @Min(0)
    private Long receiverId;
    @NotNull
    @Min(0)
    private BigDecimal amount;
    @NotNull
    private Currency currency;
}
