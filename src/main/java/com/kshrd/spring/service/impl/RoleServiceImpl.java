package com.kshrd.spring.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kshrd.spring.model.Role;
import com.kshrd.spring.repository.RoleRepository;
import com.kshrd.spring.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService {

	private RoleRepository roleRepository;
	
	@Autowired
	public RoleServiceImpl(RoleRepository roleRepository) {
		this.roleRepository = roleRepository;
	}
	
	@Override
	public List<Role> getRoles(){
		return roleRepository.getRoles();
	}
	
	@Override
	public boolean addRole(Role role){
		if(roleRepository.addRole(role)){
			return true;
		}else{
			return false;
		}
	}
	
}
