package com.adobe.service;

import java.util.List;

import com.adobe.dto.UserPostDto;
import com.adobe.exception.UserException;
import com.adobe.model.User;

public interface UserServ {

	public User registerUser(UserPostDto userPostDto) throws UserException;
	
	public User getUserById(Integer id) throws UserException;
	
	public User updateUserById(UserPostDto userPostDto, Integer id) throws UserException;
	
	public String deleteUserById(Integer id) throws UserException;
	
	public List<User> getAllUsers() throws UserException;
	
	public List<User> topFiveUsers() throws UserException;
	
}
