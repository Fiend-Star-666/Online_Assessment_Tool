package com.training.java.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.training.java.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.training.java.repositories.UserRepository;
import com.training.java.services.serviceImpl.DisplayTableImpl;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class UserController {

	@Autowired
	UserRepository userRepo;

	@Autowired
	DisplayTableImpl displayTableImpl;

	@GetMapping("/user")
	public ResponseEntity<Map<String, Object>> getAllUsersPage(
			@RequestParam(required = false) String string,
			@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "3") int size,
			@RequestParam(defaultValue = "id") String sortBy,
			@RequestParam(defaultValue = "asc") String sortDirection) {
		Map<String, Object> response = new HashMap<>();
		try {
			Sort.Direction direction = sortDirection.equalsIgnoreCase("desc") ? Sort.Direction.DESC : Sort.Direction.ASC;
			Pageable pagingSort = PageRequest.of(page, size, direction, sortBy);

			Page<User> pageUsers;

			if (string == null) {
				pageUsers = userRepo.findAll(pagingSort);
			} else {
				pageUsers = userRepo.findByNameIgnoreCaseContaining(string, pagingSort);
			}

			List<User> users = pageUsers.getContent();

			if (users.isEmpty()) {
				response.put("errorMessage", "No users found.");
				return ResponseEntity.notFound().build();
			}

			Map<User, String> formattedDates = displayTableImpl.formatDates(users);

			response.put("formattedDates", formattedDates);
			response.put("users", users);
			response.put("currentPage", pageUsers.getNumber());
			response.put("totalItems", pageUsers.getTotalElements());
			response.put("totalPages", pageUsers.getTotalPages());
			response.put("pageSize", size);
			response.put("sortBy", sortBy);
			response.put("sortDirection", sortDirection);

			return ResponseEntity.ok(response);

		} catch (Exception e) {
			response.put("errorMessage", "An error occurred while retrieving users: " + e.getMessage());
			return ResponseEntity.status(500).body(response);
		}
	}

}
