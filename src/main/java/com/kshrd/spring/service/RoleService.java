package com.kshrd.spring.service;

import java.util.List;

import com.kshrd.spring.model.Role;

public interface RoleService {

	List<Role> getRoles();
	
	boolean addRole(Role role);
	
}
