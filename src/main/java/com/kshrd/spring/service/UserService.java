package com.kshrd.spring.service;


import com.kshrd.spring.entity.Role;
import com.kshrd.spring.entity.User;
import com.kshrd.spring.entity.form.UserAddForm;
import com.kshrd.spring.entity.form.UserUpdateForm;
import com.kshrd.spring.response.Pagination;

import java.util.List;

public interface UserService {

	public List<User> findAllUser(Pagination pagination);

	public User findUserByUUID(String uuid);

	public int getUserIDByUUID(String uuid);

	public int getUserCount();

	public boolean updateUser(UserUpdateForm userUpdateForm);

	public boolean updateUserStatusByUUID(String uuid, String status);

	public boolean deleteUserByUUID(String uuid);

	public boolean insertUser(UserAddForm userAddForm);

	public User findUserByEmail(String email);
	
	public boolean insertUserRole(List<Role> role, int userId);
	
	public boolean deleteUserRoleByUserUuid(String uuid);

}
