package com.adobe.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.adobe.dto.UserPostDto;
import com.adobe.exception.UserException;
import com.adobe.model.User;
import com.adobe.service.UserServ;

@CrossOrigin
@RestController
public class UserController {

	@Autowired
	private UserServ userServ;

	
	@PostMapping("/users")
	public ResponseEntity<User> registerUser(@RequestBody UserPostDto userPostDto) throws UserException {

		User u = userServ.registerUser(userPostDto);
		return new ResponseEntity<User>(u, HttpStatus.CREATED);

	}

	@GetMapping("/users/{id}")
	public ResponseEntity<User> getUserById(@PathVariable Integer id) throws UserException {

		User u = userServ.getUserById(id);
		System.out.println(u.getPosts());
		return new ResponseEntity<User>(u, HttpStatus.OK);
	}

	@PutMapping("/users/{id}")
	public ResponseEntity<User> updateUserById(@PathVariable Integer id, @RequestBody UserPostDto userPostDto)
			throws UserException {

		User u = userServ.updateUserById(userPostDto, id);
		return new ResponseEntity<User>(u, HttpStatus.OK);

	}

	@DeleteMapping("/users/{id}")
	public ResponseEntity<String> deleteUserById(@PathVariable Integer id) throws UserException {

		String s = userServ.deleteUserById(id);
		return new ResponseEntity<>(s, HttpStatus.OK);
	}

	@GetMapping("/analytics/users")
	public ResponseEntity<List<User>> getAllUsers() throws UserException {

		List<User> list = userServ.getAllUsers();
		return new ResponseEntity<List<User>>(list, HttpStatus.OK);

	}
	
	@GetMapping("/analytics/users/top-active")
	public ResponseEntity<List<User>> getTop5Users() throws UserException{
		
		List<User> list = userServ.topFiveUsers();
		return new ResponseEntity<List<User>>(list,HttpStatus.OK);
	}
	

}
