package com.kshrd.spring.repository;

import java.util.ArrayList;

import com.kshrd.spring.model.User;

public interface UserRepository {

	ArrayList<User> getAllUsers();
	User addUser(User user);
	String[] getRoles();
	
}
