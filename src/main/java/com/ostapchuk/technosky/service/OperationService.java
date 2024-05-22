package com.ostapchuk.technosky.service;

import com.ostapchuk.technosky.dto.OperationDto;
import com.ostapchuk.technosky.mapper.OperationMapper;
import com.ostapchuk.technosky.entity.Account;
import com.ostapchuk.technosky.entity.Operation;
import com.ostapchuk.technosky.repository.OperationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

import static com.ostapchuk.technosky.entity.OperationStatus.APPLIED;

@Service
@RequiredArgsConstructor
public class OperationService {

    private final OperationMapper operationMapper;
    private final OperationRepository operationRepository;

    public Page<OperationDto> findAll(Pageable pageable) {
        return operationRepository.findAll(pageable)
              .map(operationMapper::toDto);
    }

    public Operation save(List<Account> accounts, BigDecimal amount) {
        Operation operation = Operation.builder()
              .sender(accounts.get(0))
              .receiver(accounts.get(1))
              .amount(amount)
              .status(APPLIED)
              .build();
        return operationRepository.save(operation);
    }
}
