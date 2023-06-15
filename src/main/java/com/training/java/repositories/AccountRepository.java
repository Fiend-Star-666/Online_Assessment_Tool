package com.training.java.repositories;

import com.training.java.entities.Account;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {
    Optional<Account> findByEmail(String emailId);

    Boolean existsByEmail(String email);

    List<Account> findAllByName(String string);

    List<Account> findAllByNameIgnoreCase(String name);

    Page<Account> findByNameIgnoreCaseContaining(String string, Pageable pageable);

    List<Account> findByNameIgnoreCaseContaining(String string, Sort sort);
}
