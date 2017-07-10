package com.kshrd.spring.service.impl;


import com.kshrd.spring.entity.Role;
import com.kshrd.spring.repository.RoleRepository;
import com.kshrd.spring.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserRoleServiceImpl implements RoleService {

	RoleRepository userRoleRepository;

	@Autowired
	public UserRoleServiceImpl(RoleRepository userRoleRepository) {
		this.userRoleRepository = userRoleRepository;
	}

	@Override
	public boolean insertUserRole(List<Role> roles, int userId) {
		return userRoleRepository.insertUserRole(roles, userId);
	}

	@Override
	public boolean deleteUserRoleByUserId(String uuid) {
		return userRoleRepository.deleteUserRoleByUserId(uuid);
	}

}
