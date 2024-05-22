package com.ostapchuk.technosky.repository;

import com.ostapchuk.technosky.entity.Account;
import com.ostapchuk.technosky.entity.User;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    Optional<Account> findAccountByUser(User user);
}
