package com.kshrd.spring.service.impl;

import com.kshrd.spring.entity.User;
import com.kshrd.spring.repository.UserRepository;
import com.kshrd.spring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

	UserRepository userRepository;
	
	@Autowired
	public UserServiceImpl(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public List<User> findAllUsers() {
		return userRepository.findAllUsers();
	}

	@Override
	public User findUserByUUID(String uuid) {
		return userRepository.findUserByUUID(uuid);
	}

	@Override
	public int getUserIDByUUID(String uuid) {
		return userRepository.getUserIDByUUID(uuid);
	}

	@Override
	public boolean updateUser(User user) {
		return userRepository.updateUser(user);
	}

	@Override
	public boolean updateUserStatusByUUID(String uuid, String status) {
		return userRepository.updateUserStatusByUUID(uuid, status);
	}

	@Override
	public boolean deleteUserByUUID(String uuid) {
		return userRepository.deleteUserByUUID(uuid);
	}

	@Override
	public boolean insertUser(User user) {
		user.setUuid(UUID.randomUUID().toString());
		return userRepository.insertUser(user);
	}

	@Override
	public User findUserByEmail(String email) {
		return userRepository.findUserByEmail(email);
	}

}
