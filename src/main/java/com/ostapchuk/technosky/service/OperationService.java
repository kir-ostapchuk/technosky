package com.ostapchuk.technosky.service;

import com.ostapchuk.technosky.dto.OperationDto;
import com.ostapchuk.technosky.repository.OperationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OperationService {
    private final OperationRepository operationRepository;

    public List<OperationDto> findAll() {
        return operationRepository.findAll().stream()
              .map(op -> OperationDto.builder()
                    .amount(op.getAmount())
                    .senderId(op.getSender().getId())
                    .receiverId(op.getReceiver().getId())
                    .build()
              ).toList();
    }
}
