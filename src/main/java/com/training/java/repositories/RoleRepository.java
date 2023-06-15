package com.training.java.repositories;

import com.training.java.entities.Role;
import com.training.java.entities.enums.ERole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer>{
    Optional<Role> findByName(ERole name);
}
