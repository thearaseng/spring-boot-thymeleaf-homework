package com.kshrd.spring.repository.impl;

import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kshrd.spring.data.Database;
import com.kshrd.spring.model.User;
import com.kshrd.spring.repository.UserRepository;

@Repository
public class UserRepositoryImpl implements UserRepository {

	@Autowired
	private Database database;
	
	@Override
	public ArrayList<User> getAllUsers() {
		return database.getUsers();
	}

	@Override
	public User addUser(User user) {
		return database.addUser(user);
	}
	@Override
	public String[] getRoles(){
		return database.getRoles();
	}
	
}
