package com.kshrd.spring.data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.kshrd.spring.model.Role;
import com.kshrd.spring.model.User;

public class Database {

	private ArrayList<User> users = new ArrayList<>();
	private List<Role> roles = new ArrayList<>();
	
	public Database() {
		// Add roles
		Role role = new Role();
		role.setId(1);
		role.setRoleName("Admin");
		roles.add(role);
		role = new Role();
		role.setId(2);
		role.setRoleName("Moderator");
		roles.add(role);
		role = new Role();
		role.setId(3);
		role.setRoleName("User");
		roles.add(role);
		
		// Add users
		User user = new User();
		user.setId(1);
		user.setName("Theara Seng");
		user.setProfile("https://scontent.fpnh4-1.fna.fbcdn.net/v/t1.0-1/p160x160/18622555_721664788038970_804862420506859666_n.jpg?oh=80da7d8c42f9056990c1b68d9ee8a8db&oe=59CB73AF");
		user.setRole(role);
		users.add(user);
		user = new User();
		user.setId(1);
		user.setName("Chanpheng Hor");
		user.setProfile("https://scontent.fpnh4-1.fna.fbcdn.net/v/t1.0-1/c87.0.160.160/p160x160/19149196_1963182120586122_651370099694394397_n.jpg?oh=b6df706081930c45609aa0eb54284f0c&oe=59D08732");
		user.setRole(role);
		users.add(user);
		user = new User();
		user.setId(1);
		user.setName("Bunthai Deng");
		user.setProfile("https://scontent.fpnh4-1.fna.fbcdn.net/v/t1.0-1/p160x160/16265977_1323890707672069_1454257451006852506_n.jpg?oh=278904a72fcdc0cffc4213fb0f878645&oe=59EA4A8F");
		user.setRole(role);
		users.add(user);
	}
	
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
