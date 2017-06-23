package com.kshrd.spring.service.impl;

import java.util.List;
import java.util.UUID;

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
		user.setUserHash(UUID.randomUUID().toString());
		if(userRepository.addUser(user)){
			return true;
		}else{
			return false;
		}
	}

	@Override
	public boolean updateUser(User user) {
		if(userRepository.updateUser(user)){
			return true;
		}else{
			return false;
		}
	}

	@Override
	public boolean deleteUser(String userHash) {
		if(userRepository.deleteUser(userHash)){
			return true;
		}else{
			return false;
		}
	}
	
	@Override
	public int countUsersByGender(String gender){
		return userRepository.countUsersByGender(gender);
	}
	
	@Override
	public int countAllUsers(){
		return userRepository.countAllUsers();
	}
	
}
