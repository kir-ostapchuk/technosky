package com.ostapchuk.technosky.dto;

import com.ostapchuk.technosky.entity.Account;
import lombok.Value;

import java.util.List;

@Value
public class TransferEvent {

    List<Account> accounts;
    TransferRequest request;
}
