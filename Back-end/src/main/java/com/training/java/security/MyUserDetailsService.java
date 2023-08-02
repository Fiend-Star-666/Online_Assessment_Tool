package com.training.java.security;

import com.training.java.entities.Account;
import com.training.java.repositories.AccountRepository;
import com.training.java.security.jpaModels.MyUserDetails;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private AccountRepository accountRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String emailId) throws UsernameNotFoundException {

        Account user = accountRepository.findByEmail(emailId)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + emailId));

        return MyUserDetails.build(user);
    }
}
