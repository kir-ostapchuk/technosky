package com.ostapchuk.technosky.dto;

import com.ostapchuk.technosky.validation.ValidCurrency;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder(toBuilder = true)
public class TransferRequest {

    @NotNull
    @PositiveOrZero
    private Long senderUserId;

    @NotNull
    @PositiveOrZero
    private Long receiverUserId;

    @NotNull
    @PositiveOrZero
    private BigDecimal amount;

    @ValidCurrency
    private String currency;
}
