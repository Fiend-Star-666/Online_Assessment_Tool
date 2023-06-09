package com.training.java.services;

import com.training.java.entities.User;

import java.util.List;
import java.util.Map;

public interface DisplayTableService {
    List<User> getAllUsers();
    Map<User, String> formatDates(List<User> users);
	User getUserById(int id);
	void deleteUser(int id);
	void updateUser(int id, User updatedUser);
}
