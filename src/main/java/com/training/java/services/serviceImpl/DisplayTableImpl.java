package com.training.java.services.serviceImpl;

import com.training.java.entities.User;
import com.training.java.repositories.UserRepository;
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
    private UserRepository userRepository;

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public Map<User, String> formatDates(List<User> users) {
    	SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        Map<User, String> formattedDates = new HashMap<>();
        for (User user : users) {
            String formattedDateOfBirth = formatter.format(user.getDateOfBirth());
            formattedDates.put(user, formattedDateOfBirth);
        }
        return formattedDates;
    }

    @Override
    public User getUserById(int id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteUser(int id) {
        userRepository.deleteById(id);
    }

    @Override
    public void updateUser(int id, User updatedUser) {
    	System.out.println(updatedUser);
        User existingUser = userRepository.findById(id).orElse(null);
        
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
