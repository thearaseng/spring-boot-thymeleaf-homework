package com.kshrd.spring.service;

import java.util.ArrayList;
import java.util.List;

import com.kshrd.spring.model.User;

public interface UserService {

	ArrayList<User> getAllUsers();
	User addUser(User user);
	List<String> getRoles();
	
}
