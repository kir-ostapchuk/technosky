package com.ostapchuk.technosky.controller;

import com.ostapchuk.technosky.dto.OperationDto;
import com.ostapchuk.technosky.service.OperationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class OperationControllerTest {

    @Mock
    private OperationService operationService;

    @InjectMocks
    private OperationController operationController;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(operationController).build();
    }

    @Test
    void testFindAll() throws Exception {
        // Given
        List<OperationDto> operations = Arrays.asList(
              new OperationDto(1L, 1L, 2L, 100.0),
              new OperationDto(2L, 3L, 4L, 50.0)
        );
        when(operationService.findAll()).thenReturn(operations);

        // When & Then
        mockMvc.perform(get("/operations"))
              .andExpect(status().isOk())
              .andExpect(content().json("[{\"id\":1,\"senderId\":1,\"receiverId\":2,\"amount\":100.0},{\"id\":2,\"senderId\":3,\"receiverId\":4,\"amount\":50.0}]"));
    }
}
