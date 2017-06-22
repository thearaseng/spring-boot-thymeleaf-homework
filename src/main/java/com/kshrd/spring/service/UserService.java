package com.kshrd.spring.service;

import java.util.List;

import com.kshrd.spring.model.User;

public interface UserService {

public List<User> getUsers();
	
	User getUserByHash(String userHash);
	
	boolean addUser(User user);
	
	boolean updateUser(User user);
	
	boolean deleteUser(String userHash);
	
	int countUsersByGender(String gender);
	
	int countAllUsers();
	
}
