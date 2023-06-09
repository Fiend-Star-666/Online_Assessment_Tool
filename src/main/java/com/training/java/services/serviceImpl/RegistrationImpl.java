package com.training.java.services.serviceImpl;

import com.training.java.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.training.java.repositories.UserRepository;
import com.training.java.services.RegistrationService;

@Service
public class RegistrationImpl implements RegistrationService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User registerUser(User user) {
        return userRepository.save(user);
    }
}
