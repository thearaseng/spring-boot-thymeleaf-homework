package com.kshrd.spring.service.impl;

import com.kshrd.spring.entity.Role;
import com.kshrd.spring.entity.User;
import com.kshrd.spring.entity.form.UserAddForm;
import com.kshrd.spring.entity.form.UserUpdateForm;
import com.kshrd.spring.repository.UserRepository;
import com.kshrd.spring.response.Pagination;
import com.kshrd.spring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {
	UserRepository userRepository;
	
	@Autowired
	public UserServiceImpl(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	@Override
	public User findUserByUUID(String uuid) {
		return userRepository.findUserByUUID(uuid);
	}

	@Override
	public List<User> findAllUser(Pagination pagination) {
		return userRepository.findAllUser(pagination);
	}

	@Override
	public int getUserIDByUUID(String uuid) {
		return userRepository.getUserIDByUUID(uuid);
	}

	@Override
	public int getUserCount() {
		return userRepository.getUserCount();
	}

	@Override
	public boolean updateUser(UserUpdateForm userUpdateForm) {
		User user = new User();
		user.setUsername(userUpdateForm.getUsername());
		user.setEmail(userUpdateForm.getEmail());
		user.setPassword(userUpdateForm.getPassword());
		user.setDob(userUpdateForm.getDob());
		user.setDevice(userUpdateForm.getDevice());
		user.setUuid(userUpdateForm.getUuid());
		user.setRemark(userUpdateForm.getRemark());
		user.setStatus(userUpdateForm.getStatus());
		user.setRoles(userUpdateForm.getRoles());
		user.setGender(userUpdateForm.getGender());
		return userRepository.updateUser(user);
	}

	@Override
	public boolean updateUserStatusByUUID(String uuid, String status) {
		return userRepository.updateUserStatusByUUID(uuid, status);
	}

	@Override
	public boolean deleteUserByUUID(String uuid) {
		return userRepository.deleteUserByUUID(uuid);
	}

	@Override
	public boolean insertUser(UserAddForm userAddForm) {
		User user = new User();
		userAddForm.setUuid(UUID.randomUUID().toString());
		user.setUuid(userAddForm.getUuid());
		user.setUsername(userAddForm.getUsername());
		user.setEmail(userAddForm.getEmail());
		user.setPassword(userAddForm.getPassword());
		user.setDob(userAddForm.getDob());
		user.setGender(userAddForm.getGender());
		user.setRemark(userAddForm.getRemark());
		user.setRoles(userAddForm.getRoles());
		return userRepository.insertUser(user);
	}

	@Override
	public User findUserByEmail(String email) {
		return userRepository.findUserByEmail(email);
	}

	@Override
	public boolean deleteUserRoleByUserUuid(String uuid) {
		return userRepository.deleteUserRoleByUserUuid(uuid);
	}

	@Override
	public boolean insertUserRole(List<Role> role, int userId) {
		return userRepository.insertUserRole(role, userId);
	}

}
