package com.ostapchuk.technosky.service;

import com.ostapchuk.technosky.entity.Account;
import com.ostapchuk.technosky.entity.Operation;
import com.ostapchuk.technosky.entity.User;
import com.ostapchuk.technosky.mapper.OperationMapper;
import com.ostapchuk.technosky.repository.OperationRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.math.BigDecimal;
import java.util.List;

import static com.ostapchuk.technosky.entity.Currency.USD;
import static com.ostapchuk.technosky.entity.DocumentType.PASSPORT;
import static com.ostapchuk.technosky.entity.OperationStatus.APPLIED;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

@ExtendWith(MockitoExtension.class)
class OperationServiceTest {

    public static final List<Operation> OPERATIONS = buildOperations();

    @InjectMocks
    private OperationService operationService;

    @Mock
    private OperationMapper operationMapper;

    @Mock
    private OperationRepository operationRepository;

    @Test
    void findAll() {
        // given
        var pageRequest = PageRequest.of(1, 3);
        var page = new PageImpl<>(OPERATIONS);
        // when
        doReturn(page).when(operationRepository).findAll(pageRequest);
        // then
        var actualResult = operationService.findAll(pageRequest);
        assertThat(actualResult.getContent()).hasSize(3);
        verify(operationMapper, times(3)).toDto(any(Operation.class));
        verifyNoMoreInteractions(operationMapper, operationRepository);
    }

    @Test
    void save() {
    }


    private static List<Operation> buildOperations() {
        Account sender = buildAccount(1L);
        Account receiver = buildAccount(2L);
        return List.of(
              buildOperation(1L, sender, receiver),
              buildOperation(2L, sender, receiver),
              buildOperation(3L, sender, receiver)
        );
    }

    private static Account buildAccount(Long id) {
        return Account.builder()
              .id(id)
              .user(buildUser(id))
              .balance(new BigDecimal("100"))
              .currency(USD)
              .build();
    }

    private static User buildUser(Long id) {
        return User.builder()
              .id(id)
              .serial("test-serial" + id)
              .documentType(PASSPORT)
              .build();
    }

    private static Operation buildOperation(Long id, Account sender, Account receiver) {
        return Operation.builder()
              .id(id)
              .status(APPLIED)
              .sender(sender)
              .receiver(receiver)
              .amount(new BigDecimal("10"))
              .build();
    }
}