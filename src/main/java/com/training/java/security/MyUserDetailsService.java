package com.training.java.security;

import com.training.java.entities.abstrct.Account;
import com.training.java.repositories.AccountRepository;
import com.training.java.security.jpaModels.MyUserDetails;
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
    public UserDetails loadUserByUsername(String emailId) throws UsernameNotFoundException {

        Account user = accountRepository.findByEmail(emailId);

        System.out.println("loadbyusername in myuserdetailsService user: "+user);

        if(user==null){

            throw new UsernameNotFoundException("Not found : " + emailId);
        }

        return new MyUserDetails(user);
    }
}
