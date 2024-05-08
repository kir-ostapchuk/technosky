package com.ostapchuk.technosky.service;

import com.ostapchuk.technosky.dto.OperationDto;
import com.ostapchuk.technosky.repository.OperationRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.mockito.Mockito.when;

class OperationServiceTest {

    @Mock
    private OperationRepository operationRepository;

    @InjectMocks
    private OperationService operationService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testFindAll() {
        // Given
        // Mock the behavior of OperationRepository
        when(operationRepository.findAll()).thenReturn(/* Provide a list of test data here */);

        // When
        List<OperationDto> operations = operationService.findAll();

        // Then
        // Add assertions to validate the result
        // For example, you can check the size of the returned list or specific properties of each OperationDto
    }
}
