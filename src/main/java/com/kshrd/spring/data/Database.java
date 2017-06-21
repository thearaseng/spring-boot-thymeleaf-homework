package com.kshrd.spring.data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.kshrd.spring.model.Role;
import com.kshrd.spring.model.User;

public class Database {

	private ArrayList<User> users = new ArrayList<>();
	private List<Role> roles = new ArrayList<>();
	
	public ArrayList<User> getUsers(){
		return users;
	}
	
	public User addUser(User user){
		users.add(user);
		return user;
	}
	
	public List<Role> getRoles(){
		return roles;
	}
	
}
