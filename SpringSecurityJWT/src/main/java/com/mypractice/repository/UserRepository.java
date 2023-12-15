package com.mypractice.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mypractice.entities.Role;
import com.mypractice.entities.User;

public interface UserRepository extends JpaRepository<User,Integer> {
	
	Optional<User> findByEmail(String email);
	
	User findByRole(Role role);
}
