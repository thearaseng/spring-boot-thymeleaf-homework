package com.kshrd.spring.repository;

import java.util.ArrayList;
import java.util.List;

import com.kshrd.spring.model.Role;
import com.kshrd.spring.model.User;

public interface UserRepository {

	ArrayList<User> getAllUsers();
	User addUser(User user);
	List<Role> getRoles();
	
}
