package com.training.java.controllers;

import com.training.java.entities.Account;
import com.training.java.services.DisplayTableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class DisplayTableController {

    @Autowired
    private DisplayTableService displayTableService;

    @GetMapping
    public ResponseEntity<List<Account>> showAllUsers() {
        List<Account> users = displayTableService.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/edit/{id}")
    public ResponseEntity<Account> editUser(@PathVariable int id) {
        Account user = displayTableService.getUserById(id);
        if (user != null) {
            return new ResponseEntity<>(user, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<Void> updateUser(@PathVariable int id, @RequestBody Account updatedUser) {
        displayTableService.updateUser(id, updatedUser);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable int id) {
        displayTableService.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
