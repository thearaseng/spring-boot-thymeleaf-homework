package com.kshrd.spring.service;

import java.util.List;

import com.kshrd.spring.model.User;

public interface UserService {

public List<User> getUsers();
	
	public User getUserByHash(String userHash);
	
	public boolean addUser(User user);
	
	public boolean updateUser(User user);
	
	public boolean deleteUser(String userHash);
	
}
