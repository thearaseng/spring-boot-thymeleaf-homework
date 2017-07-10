package com.kshrd.spring.service;

import com.kshrd.spring.entity.Role;

import java.util.List;

public interface UserRoleService {

	public boolean insertUserRole(List<Role> roles, int userId);

	public boolean deleteUserRoleByUserId(String uuid);

}
