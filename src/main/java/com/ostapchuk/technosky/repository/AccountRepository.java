package com.ostapchuk.technosky.repository;

import com.ostapchuk.technosky.entity.Account;
import com.ostapchuk.technosky.entity.Client;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends CrudRepository<Account, Long> {

    Account findAccountByClient(Client client);
}
