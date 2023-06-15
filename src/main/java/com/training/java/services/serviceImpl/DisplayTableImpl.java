package com.training.java.services.serviceImpl;

import com.training.java.entities.Account;
import com.training.java.repositories.AccountRepository;
import com.training.java.services.DisplayTableService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DisplayTableImpl implements DisplayTableService {

    @Autowired
    private AccountRepository userRepository;

    @Override
    public List<Account> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public Map<Account, String> formatDates(List<Account> users) {
    	SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        Map<Account, String> formattedDates = new HashMap<>();
        for (Account user : users) {
            String formattedDateOfBirth = formatter.format(user.getDateOfBirth());
            formattedDates.put(user, formattedDateOfBirth);
        }
        return formattedDates;
    }

    @Override
    public Account getUserById(int id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteUser(int id) {
        userRepository.deleteById(id);
    }

    @Override
    public void updateUser(int id, Account updatedUser) {
    	System.out.println(updatedUser);
        Account existingUser = userRepository.findById(id).orElse(null);
        
        if (existingUser != null) {
            existingUser.setName(updatedUser.getName());
            existingUser.setDateOfBirth(updatedUser.getDateOfBirth());
            //TODO: Add the rest of the fields
            existingUser.setCity(updatedUser.getCity());


            existingUser.setState(updatedUser.getState());
            userRepository.save(existingUser);
        }
    }
	
}
