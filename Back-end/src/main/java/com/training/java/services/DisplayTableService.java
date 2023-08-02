package com.training.java.services;

import com.training.java.entities.Account;

import java.util.List;
import java.util.Map;

public interface DisplayTableService {
    List<Account> getAllUsers();

    Map<Account, String> formatDates(List<Account> users);

    Account getUserById(int id);

    void deleteUser(int id);

    void updateUser(int id, Account updatedUser);
}
