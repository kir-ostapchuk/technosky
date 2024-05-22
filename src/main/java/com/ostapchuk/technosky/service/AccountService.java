package com.ostapchuk.technosky.service;

import com.ostapchuk.technosky.dto.TransferRequest;
import com.ostapchuk.technosky.entity.Account;
import com.ostapchuk.technosky.entity.Operation;
import com.ostapchuk.technosky.exception.AccountBalanceException;
import com.ostapchuk.technosky.exception.EntityNotFoundException;
import com.ostapchuk.technosky.repository.AccountRepository;
import com.ostapchuk.technosky.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;
    private final OperationService operationService;
    private final UserRepository userRepository;

    @Transactional
    public Operation transfer(TransferRequest request) {
        Account sender = findAndValidateAccount(request);
        sender.setBalance(sender.getBalance().subtract(request.getAmount()));
        Account receiver = findAccountByUserId(request.getReceiverUserId());
        receiver.setBalance(receiver.getBalance().add(request.getAmount()));
        List<Account> updatedAccounts = accountRepository.saveAll(List.of(sender, receiver));
        return operationService.save(updatedAccounts, request.getAmount());
    }

    private Account findAndValidateAccount(TransferRequest request) {
        return Optional.of(findAccountByUserId(request.getSenderUserId()))
              .filter(account -> account.getBalance().compareTo(request.getAmount()) > 0)
              .orElseThrow(() -> new AccountBalanceException(request.getSenderUserId()));
    }

    private Account findAccountByUserId(Long userId) {
        return userRepository.findById(userId)
              .flatMap(accountRepository::findAccountByUser)
              .orElseThrow(() -> new EntityNotFoundException("Cannot find account or user: " + userId));
    }
}
