package com.kshrd.spring.repository;

import java.util.List;

import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import com.kshrd.spring.model.User;

@Repository
public interface UserRepository {

	@Select("SELECT "
			+ "	U.id, "
			+ "	U.user_name, "
			+ "	U.email, "
			+ "	U.password, "
			+ "	U.gender, "
			+ "	U.user_hash,"
			+ "	U.profile_url,"
			+ "	U.phone_number,"
			+ "	U.status,"
			+ " R.id AS role_id,"
			+ " R.role_name"
			+ " FROM "
			+ "	users U INNER JOIN role R ON U.role_id = R.id")
	@Results(value={
			@Result(property="userName" , column="user_name"),
			@Result(property="userHash" , column="user_hash"),
			@Result(property="profileUrl" , column="profile_url"),
			@Result(property="phoneNumber" , column="phone_number"),
			@Result(property="role.id", column="role_id"),
			@Result(property="role.roleName", column="role_name")
	})
	public List<User> getUsers();
	
	public User getUserByHash(String userHash);
	
	public boolean addUser(User user);
	
	public boolean updateUser(User user);
	
	public boolean deleteUser(String userHash);
	
}
