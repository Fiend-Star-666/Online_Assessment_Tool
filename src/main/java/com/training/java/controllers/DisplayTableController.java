package com.training.java.controllers;

import com.training.java.entities.Student;
import com.training.java.entities.enums.StatesOfIndia;
import com.training.java.entities.enums.StreamOfStudents;
import com.training.java.services.DisplayTableService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/students")
public class DisplayTableController {

    @Autowired
    private DisplayTableService displayTableService;

    @GetMapping
    public ResponseEntity<List<Student>> showAllStudents() {
        List<Student> students = displayTableService.getAllStudents();
        return new ResponseEntity<>(students, HttpStatus.OK);
    }

    @GetMapping("/edit/{id}")
    public ResponseEntity<Student> editStudent(@PathVariable int id) {
        Student student = displayTableService.getStudentById(id);
        if (student != null) {
            return new ResponseEntity<>(student, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<Void> updateStudent(@PathVariable int id, @RequestBody Student updatedStudent) {
        displayTableService.updateStudent(id, updatedStudent);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable int id) {
        displayTableService.deleteStudent(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
