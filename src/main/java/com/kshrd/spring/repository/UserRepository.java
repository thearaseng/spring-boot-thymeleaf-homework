package com.kshrd.spring.repository;

import java.util.List;

import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import com.kshrd.spring.model.User;

public interface UserRepository {

	@Select("SELECT "
			+ "	U.id, "
			+ "	U.user_name, "
			+ "	U.email, "
			+ "	U.password, "
			+ "	U.gender, "
			+ "	U.user_hash,"
			+ " R.role_name"
			+ " FROM "
			+ "	users U INNER JOIN role R ON U.role_id = R.id")
	@Results(value={
			@Result(property="userHash" , column="user_hash"),
			@Result(property="role.roleName", column="role_name")
	})
	public List<User> getUsers();
	
}
