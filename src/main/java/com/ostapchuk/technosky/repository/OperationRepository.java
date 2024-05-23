package com.ostapchuk.technosky.repository;

import com.ostapchuk.technosky.entity.Operation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OperationRepository extends JpaRepository<Operation, Long> {

    @EntityGraph(attributePaths = {"sender", "receiver", "sender.user", "receiver.user"})
    Page<Operation> findAll(Pageable pageable);
}
