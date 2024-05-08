package com.ostapchuk.technosky.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.ostapchuk.technosky.dto.TransferRequest;
import com.ostapchuk.technosky.dto.TransferResponse;
import com.ostapchuk.technosky.entity.OperationStatus;
import com.ostapchuk.technosky.service.AccountService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = AccountController.class)
class AccountControllerTest {

    @MockBean
    private AccountService accountService;

    @Autowired
    private MockMvc mockMvc;

    private final ObjectMapper mapper = new JsonMapper();

    @Test
    void testTransfer() throws Exception {
        // Given
        TransferRequest transferRequest = TransferRequest.builder().senderId(1L).receiverId(2L)
              .amount(new BigDecimal("100.00")).build();
        TransferResponse expectedResponse = TransferResponse.builder().operationStatus(OperationStatus.APPLIED.name())
              .build();
        when(accountService.transfer(transferRequest)).thenReturn(expectedResponse);

        // When & Then
        mockMvc.perform(post("/transfer")
                    .contentType("application/json")
                    .content(mapper.writeValueAsString(transferRequest)))
              .andExpect(status().isOk())
              .andExpect(content().json(mapper.writeValueAsString(expectedResponse)));
    }
}
