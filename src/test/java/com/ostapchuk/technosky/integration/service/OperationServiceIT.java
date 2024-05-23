package com.ostapchuk.technosky.integration.service;

import com.ostapchuk.technosky.integration.IntegrationTestBase;
import com.ostapchuk.technosky.service.OperationService;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.jdbc.Sql;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Sql("/test-data.sql")
@RequiredArgsConstructor
class OperationServiceIT extends IntegrationTestBase {

    private final OperationService operationService;

    @Test
    void findAll() {
        var pageRequest = PageRequest.of(0, 3);
        var actualResult = operationService.findAll(pageRequest);
        assertEquals(3, actualResult.getContent().size());
    }
}
