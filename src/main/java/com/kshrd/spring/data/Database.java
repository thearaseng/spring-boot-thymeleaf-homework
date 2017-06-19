package com.kshrd.spring.data;

import java.util.ArrayList;
import com.kshrd.spring.model.User;

public class Database {

	private ArrayList<User> users = new ArrayList<>();
	
	public ArrayList<User> getUsers(){
		User user = new User();
		user.setId(1);
		user.setName("Theara");
		user.setProfile("prisfsdfdf/sdfs/f/sd/fds");
		user.setRole("admin");
		users.add(user);
		return users;
	}
	
	public User addUser(User user){
		users.add(user);
		return user;
	}
	
	public User updateUser(User user){
		users.set(users.indexOf(findUser(user.getId())), user);
		return user;
	}
	
	private User findUser(int id){
		for(User user : users){
			if(user.getId() == id) return user;
		}
		return null;
	}
}
