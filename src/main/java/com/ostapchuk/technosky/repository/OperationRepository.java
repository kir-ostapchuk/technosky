package com.ostapchuk.technosky.repository;

import com.ostapchuk.technosky.entity.Operation;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface OperationRepository extends CrudRepository<Operation, Long> {

    @EntityGraph("Operation.senderAndReceiver")
    List<Operation> findAll();
}
