package com.ostapchuk.technosky.service;

import com.ostapchuk.technosky.entity.Account;
import com.ostapchuk.technosky.exception.EntityNotFoundException;
import com.ostapchuk.technosky.repository.AccountRepository;
import com.ostapchuk.technosky.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

class AccountServiceTest {

    @Mock
    private AccountRepository accountRepository;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private AccountService accountService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testFindAccountByUserId() {
        // Given
        Long userId = 1L;
        Account account = new Account();
        when(accountRepository.findById(userId)).thenReturn(Optional.of(account));

        // When
        Account result = accountService.findAccountByUserId(userId);

        // Then
        assertEquals(account, result);
    }

    @Test
    void testFindAccountByUserIdNotFound() {
        // Given
        Long userId = 2L;
        when(accountRepository.findById(userId)).thenReturn(Optional.empty());

        // When & Then
        assertThrows(EntityNotFoundException.class, () -> accountService.findAccountByUserId(userId));
    }
}
