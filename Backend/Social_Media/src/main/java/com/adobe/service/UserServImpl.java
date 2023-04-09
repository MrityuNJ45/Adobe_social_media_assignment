package com.adobe.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.adobe.dto.UserPostDto;
import com.adobe.exception.UserException;
import com.adobe.model.User;
import com.adobe.repo.UserDao;

@Service
public class UserServImpl implements UserServ {

	@Autowired
	private UserDao userDao;

	@Override
	public User registerUser(UserPostDto userPostDto) throws UserException {

		Optional<User> opt = userDao.findByEmail(userPostDto.getEmail());

		if (opt.isPresent()) {
			throw new UserException("Email Id already registered.....");
		}

		User u = new User();

		u.setName(userPostDto.getName());
		u.setEmail(userPostDto.getEmail());
		u.setBio(userPostDto.getBio());
		u.setCreated_at(LocalDateTime.now());

		return userDao.save(u);
	}

	@Override
	public User getUserById(Integer id) throws UserException {

		Optional<User> opt = userDao.findById(id);
		if (opt.isEmpty()) {
			throw new UserException("Invalid User ID : " + id);
		}

		return opt.get();
	}

	@Override
	public User updateUserById(UserPostDto userPostDto, Integer id) throws UserException {

		Optional<User> opt = userDao.findById(id);
		if (opt.isEmpty()) {
			throw new UserException("Invalid User ID : " + id);
		}
		User u = opt.get();
		u.setBio(userPostDto.getBio());
		u.setName(userPostDto.getName());
		u.setUpdated_at(LocalDateTime.now());
		return userDao.save(u);

	}

	@Override
	public String deleteUserById(Integer id) throws UserException {

		Optional<User> opt = userDao.findById(id);
		if (opt.isEmpty()) {
			throw new UserException("Invalid User ID : " + id);
		}
		userDao.delete(opt.get());
		return "User deleted successfully : " + id;
	}

	@Override
	public List<User> getAllUsers() throws UserException {

		List<User> list = userDao.findAll();
		if (list.size() == 0) {
			throw new UserException("No users found....");
		}

		return list;
	}

	@Override
	public List<User> topFiveUsers() throws UserException {

		List<User> list = userDao.findAll();
		if (list.size() == 0) {
			throw new UserException("No records found....");
		}

		List<User> top5 = list.stream().sorted((a, b) -> b.getPosts().size() - a.getPosts().size()).limit(5).toList();

		return top5;
	}

}
