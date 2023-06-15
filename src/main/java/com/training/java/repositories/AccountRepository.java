package com.training.java.repositories;

import com.training.java.entities.abstrct.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Integer>{
    Account findByEmail(String email);

}
