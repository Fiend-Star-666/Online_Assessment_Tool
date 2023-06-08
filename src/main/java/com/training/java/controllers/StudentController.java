package com.training.java.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.training.java.entities.Student;
import com.training.java.repositories.StudentRepository;
import com.training.java.services.serviceImpl.DisplayTableImpl;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class StudentController {

	@Autowired
	StudentRepository studentRepo;

	@Autowired
	DisplayTableImpl displayTableImpl;

	@GetMapping("/student")
	public ResponseEntity<Map<String, Object>> getAllStudentsPage(
			@RequestParam(required = false) String string,
			@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "3") int size,
			@RequestParam(defaultValue = "id") String sortBy,
			@RequestParam(defaultValue = "asc") String sortDirection) {
		Map<String, Object> response = new HashMap<>();
		try {
			Sort.Direction direction = sortDirection.equalsIgnoreCase("desc") ? Sort.Direction.DESC : Sort.Direction.ASC;
			Pageable pagingSort = PageRequest.of(page, size, direction, sortBy);

			Page<Student> pageStudents;

			if (string == null) {
				pageStudents = studentRepo.findAll(pagingSort);
			} else {
				pageStudents = studentRepo.findByNameIgnoreCaseContaining(string, pagingSort);
			}

			List<Student> students = pageStudents.getContent();

			if (students.isEmpty()) {
				response.put("errorMessage", "No students found.");
				return ResponseEntity.notFound().build();
			}

			Map<Student, String> formattedDates = displayTableImpl.formatDates(students);

			response.put("formattedDates", formattedDates);
			response.put("students", students);
			response.put("currentPage", pageStudents.getNumber());
			response.put("totalItems", pageStudents.getTotalElements());
			response.put("totalPages", pageStudents.getTotalPages());
			response.put("pageSize", size);
			response.put("sortBy", sortBy);
			response.put("sortDirection", sortDirection);

			return ResponseEntity.ok(response);

		} catch (Exception e) {
			response.put("errorMessage", "An error occurred while retrieving students: " + e.getMessage());
			return ResponseEntity.status(500).body(response);
		}
	}

}
