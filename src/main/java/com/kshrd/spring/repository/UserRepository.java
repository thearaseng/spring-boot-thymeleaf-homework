package com.kshrd.spring.repository;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
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
	
	@Select("SELECT * FROM users U"
			+ " JOIN role R ON U.role_id = R.id"
			+ " WHERE user_hash = #{user_hash}")
	@Results(value={
			@Result(property="userName" , column="user_name"),
			@Result(property="phoneNumber" , column="phone_number"),
			@Result(property="userHash" , column="user_hash"),
			@Result(property="profileUrl" , column="profile_url"),
			@Result(property="userHash" , column="user_hash"),
			@Result(property="role.id", column="role_id"),
			@Result(property="role.roleName", column="role_name")
	})
	public User getUserByHash(@Param("user_hash") String userHash);
	
//	@Insert("INSERT INTO users (user_name, email, gender, phone_number, status, user_hash, password, profile_url) VALUES("
//			+ "#{user.userName, }"
//			+ "#{user.email, }"
//			+ "#{user.gender, }"
//			+ "#{user.phoneNumber, }"
//			+ "#{user.status, }"
//			+ "#{user.userHash, }"
//			+ "#{user.password, }"
//			+ "#{user.profileUrl}"
//			+ ")")
//	public boolean addUser(@Param("user") User user);
	
	@Insert("INSERT INTO users ("
			+ "	user_name, "
			+ "	email, "
			+ "	gender, "
			+ "	phone_number, "
			+ "	password, "
			+ "	role_id, "
			+ "	profile_url"
			+ "	) VALUES ("
			+ "	#{user.userName},"
			+ "	#{user.email},"
			+ "	#{user.gender},"
			+ "	#{user.phoneNumber}, "
			+ "	#{user.userHash},"
			+ "	#{user.password},"
			+ "	#{user.role.id},"
			+ "	#{user.profileUrl}"
			+ ")")
	public boolean addUser(@Param("user") User user);
	
	@Update("UPDATE users SET "
			+ "user_name=#{user.userName}, "
			+ "email=#{user.email}, "
			+ "gender=#{user.gender}, "
			+ "phone_number=#{user.phoneNumber}, "
			+ "password=#{user.password}, "
			+ "role_id=#{user.role.id}, "
			+ "profile_url=#{user.profileUrl}"
			+ " WHERE user_hash = #{user.userHash}")
	public boolean updateUser(@Param("user") User user);
	
	@Delete("DELETE FROM users WHERE user_hash=#{user_hash}")
	public boolean deleteUser(String user_hash);
	
	
}
