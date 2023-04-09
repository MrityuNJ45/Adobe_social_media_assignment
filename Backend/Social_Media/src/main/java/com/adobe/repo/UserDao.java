package com.adobe.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.adobe.model.User;

public interface UserDao extends JpaRepository<User, Integer>{

	public Optional<User> findByEmail(String email);
	
	
}
