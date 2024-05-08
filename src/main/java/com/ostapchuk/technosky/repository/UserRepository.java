package com.ostapchuk.technosky.repository;

import com.ostapchuk.technosky.entity.Client;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<Client, Long> {
}
