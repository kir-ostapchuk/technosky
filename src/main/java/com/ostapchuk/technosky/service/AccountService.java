package com.ostapchuk.technosky.service;

import com.ostapchuk.technosky.dto.TransferRequest;
import com.ostapchuk.technosky.dto.TransferResponse;
import com.ostapchuk.technosky.entity.Account;
import com.ostapchuk.technosky.entity.Operation;
import com.ostapchuk.technosky.exception.AccountBalanceException;
import com.ostapchuk.technosky.exception.EntityNotFoundException;
import com.ostapchuk.technosky.repository.AccountRepository;
import com.ostapchuk.technosky.repository.OperationRepository;
import com.ostapchuk.technosky.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.ostapchuk.technosky.entity.OperationStatus.APPLIED;

@Service
@RequiredArgsConstructor
public class AccountService {
    private final OperationRepository operationRepository;
    private final AccountRepository accountRepository;
    private final UserRepository userRepository;

    @Transactional
    public TransferResponse transfer(TransferRequest request) {
        List<Account> updatedAccounts = doTransfer(request);
        addOperation(request, updatedAccounts);
        return TransferResponse.builder().operationStatus(APPLIED.name()).build();
    }

    private void addOperation(TransferRequest request, List<Account> updatedAccounts) {
        Operation operation = Operation.builder()
              .sender(updatedAccounts.get(0))
              .receiver(updatedAccounts.get(1))
              .amount(request.getAmount())
              .status(APPLIED)
              .build();
        operationRepository.save(operation);
    }

    private List<Account> doTransfer(TransferRequest request) {
        Account sender = findAndValidateAccount(request);
        sender.setBalance(sender.getBalance().subtract(request.getAmount()));
        Account receiver = findAccountByUserId(request.getReceiverId());
        receiver.setBalance(receiver.getBalance().add(request.getAmount()));
        List<Account> updatedAccounts = List.of(sender, receiver);
        accountRepository.saveAll(updatedAccounts);
        return List.of(sender, receiver);
    }

    private Account findAndValidateAccount(TransferRequest request) {
        Account sender = findAccountByUserId(request.getSenderId());
        if (sender.getBalance().compareTo(request.getAmount()) < 0) {
            throw new AccountBalanceException(
                  "Account of " + request.getSenderId() + " does not have enough balance to transfer");
        }
        return sender;
    }

    private Account findAccountByUserId(Long userId) {
        return userRepository.findById(userId)
              .map(accountRepository::findAccountByClient)
              .orElseThrow(() -> new EntityNotFoundException("Cannot find account of the user=" + userId));
    }
}
