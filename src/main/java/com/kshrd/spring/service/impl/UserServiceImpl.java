package com.kshrd.spring.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kshrd.spring.model.User;
import com.kshrd.spring.repository.UserRepository;

@Service
public class UserServiceImpl implements com.kshrd.spring.service.UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public ArrayList<User> getAllUsers() {
		return userRepository.getAllUsers();
	}

	@Override
	public User addUser(User user) {
		return userRepository.addUser(user);
	}

	@Override
	public List<String> getRoles(){
		return userRepository.getRoles();
	}
	
}
