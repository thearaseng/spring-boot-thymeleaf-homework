package com.kshrd.spring.service.impl;


import com.kshrd.spring.entity.Role;
import com.kshrd.spring.repository.UserRoleRepository;
import com.kshrd.spring.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserRoleServiceImpl implements UserRoleService {

	UserRoleRepository userRoleRepository;

	@Autowired
	public UserRoleServiceImpl(UserRoleRepository userRoleRepository) {
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
