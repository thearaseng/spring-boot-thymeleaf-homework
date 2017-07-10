package com.kshrd.spring.service;


import com.kshrd.spring.entity.User;

import java.util.List;

public interface UserService {

	public List<User> findAllUsers();

	public User findUserByUUID(String uuid);

	public int getUserIDByUUID(String uuid);

	public boolean updateUser(User user);

	public boolean updateUserStatusByUUID(String uuid, String status);

	public boolean deleteUserByUUID(String uuid);

	public boolean insertUser(User user);

	public User findUserByEmail(String email);

}
