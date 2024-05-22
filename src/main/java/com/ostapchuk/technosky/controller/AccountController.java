package com.ostapchuk.technosky.controller;

import com.ostapchuk.technosky.dto.TransferRequest;
import com.ostapchuk.technosky.dto.TransferResponse;
import com.ostapchuk.technosky.entity.Operation;
import com.ostapchuk.technosky.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/api/v1/accounts")
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;

    @PostMapping("/transfer")
    @ResponseStatus(CREATED)
    public TransferResponse transfer(@Validated @RequestBody final TransferRequest transferRequest) {
        final Operation operation = accountService.transfer(transferRequest);
        return TransferResponse.builder()
              .operationId(operation.getId())
              .operationStatus(operation.getStatus().name())
              .build();
    }
}