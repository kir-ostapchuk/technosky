package com.ostapchuk.technosky.mapper;

import com.ostapchuk.technosky.dto.OperationDto;
import com.ostapchuk.technosky.entity.Operation;
import org.springframework.stereotype.Component;

@Component
public class OperationMapper {

    public OperationDto toDto(final Operation operation) {
        return OperationDto.builder()
              .id(operation.getId())
              .amount(operation.getAmount())
              .senderId(operation.getSender().getId())
              .receiverId(operation.getReceiver().getId())
              .build();
    }

}
