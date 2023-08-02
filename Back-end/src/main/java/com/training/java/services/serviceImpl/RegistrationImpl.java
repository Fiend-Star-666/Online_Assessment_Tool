package com.training.java.services.serviceImpl;

import com.training.java.entities.Account;
import com.training.java.repositories.AccountRepository;
import com.training.java.services.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegistrationImpl implements RegistrationService {

    @Autowired
    private AccountRepository userRepository;

    @Override
    public Account registerUser(Account user) {
        return userRepository.save(user);
    }
}
