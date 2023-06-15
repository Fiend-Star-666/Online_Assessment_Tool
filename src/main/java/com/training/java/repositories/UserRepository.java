package com.training.java.repositories;

import com.training.java.entities.User;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
	List<User> findAllByName(String string);
	List<User> findAllByNameIgnoreCase(String name);
	Page<User> findByNameIgnoreCaseContaining(String string, Pageable pageable);
	List<User> findByNameIgnoreCaseContaining(String string, Sort sort);
}
