package com.kshrd.spring.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.kshrd.spring.model.User;
import com.kshrd.spring.repository.UserRepository;
import com.kshrd.spring.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	private UserRepository userRepository;
	
	@Autowired
	public UserServiceImpl(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	@Override
	public List<User> getUsers() {
		return userRepository.getUsers();
	}

	@Override
	public User getUserByHash(String userHash) {
		return userRepository.getUserByHash(userHash);
	}

	@Override
	public boolean addUser(User user) {
		if(userRepository.addUser(user)){
			return true;
		}else{
			return false;
		}
	}

	@Override
	public boolean updateUser(User user) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteUser(String userHash) {
		if(userRepository.deleteUser(userHash)){
			return true;
		}else{
			return false;
		}
	}
	
}
