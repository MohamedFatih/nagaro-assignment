package com.nagaro.engine.repository;

import com.nagaro.engine.model.Account;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    
}