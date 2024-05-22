package com.ostapchuk.technosky.controller;

import com.ostapchuk.technosky.dto.OperationDto;
import com.ostapchuk.technosky.service.OperationService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/operations")
@RequiredArgsConstructor
public class OperationController {

    private final OperationService operationService;

    @GetMapping
    public Page<OperationDto> findAll(final Pageable p) {
        return operationService.findAll(p);
    }
}
