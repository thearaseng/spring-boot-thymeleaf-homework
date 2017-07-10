package com.kshrd.spring.repository;

import com.kshrd.spring.entity.Role;
import com.kshrd.spring.entity.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository {

	@Select("SELECT" +
				" id," +
				" username," +
				" email," +
				" password," +
				" dob," +
				" gender," +
				" device," +
				" remark," +
				" status," +
				" uuid" +
			" FROM" +
				" users" +
			" WHERE" +
				" status = '1'")
	@Results(value = {
			@Result(property = "id", column = "id"),
			@Result(property = "roles", column = "id", many = @Many(select = "findRolesById"))
		}
	)
	public List<User> findAllUsers();
	
	@Select("SELECT" +
				" id," +
				" name," +
				" remark," +
				" status," +
				" uuid" +
			" FROM" +
				" roles r" +
			" JOIN user_roles ur" +
			" ON ur.role_id = r. ID" +
			" WHERE" +
				" ur.user_id = #{userid}")
	public List<Role> findRolesById(@Param("userid") int userid);

	@Select("SELECT" +
				" id," +
				" username," +
				" email," +
				" password," +
				" dob," +
				" gender," +
				" device," +
				" remark," +
				" status," +
				" uuid" +
			" FROM" +
				" users" +
			" WHERE" +
				" status = '1' AND uuid = #{uuid}")
	@Results(
		value = {
				@Result(property = "id", column = "id"),
				@Result(property = "roles", column = "id", many = @Many(select = "findRolesById"))
		}
	)
	public User findUserByUUID(@Param("uuid") String uuid);

	@Select("SELECT id FROM users WHERE uuid = #{uuid}")
	public int getUserIDByUUID(@Param("uuid") String uuid);

	@Update("UPDATE users SET " +
				"username = #{user.username}, " +
				"email = #{user.email}, " +
				"password = #{user.password}, " +
				"dob = #{user.dob}, " +
				"gender = #{user.gender}, " +
				"device = #{user.device}, " +
				"remark = #{user.remark}, " +
				"status = #{user.status}" +
			" WHERE " +
				"uuid = #{user.uuid}")
	public boolean updateUser(@Param("user") User user);
	
	@Update("UPDATE users SET status = #{status} WHERE uuid = #{uuid}")
	public boolean updateUserStatusByUUID(@Param("uuid") String uuid, @Param("status") String status);

	@Delete("DELETE FROM users WHERE uuid = #{uuid}")
	public boolean deleteUserByUUID(@Param("uuid") String uuid);
	
	@Insert("INSERT INTO users(" +
				"username, " +
				"email, " +
				"password, " +
				"dob, " +
				"gender, " +
				"device, " +
				"remark, " +
				"status, " +
				"uuid) " +
			"VALUES (" +
				"#{user.username}, " +
				"#{user.email}, " +
				"#{user.password}, " +
				"#{user.dob}, " +
				"#{user.gender}, " +
				"#{user.device}, " +
				"#{user.remark}, " +
				"#{user.status}, " +
				"#{user.uuid})")
	@SelectKey(
		statement = "SELECT last_value FROM users_id_seq",
		keyProperty = "user.id",
		keyColumn = "last_value",
		before=false,
		resultType=int.class
	)
	public boolean insertUser(@Param("user") User user);

	@Select("SELECT" +
				" id," +
				" username," +
				" email," +
				" password," +
				" dob," +
				" gender," +
				" device," +
				" remark," +
				" status," +
				" uuid" +
			" FROM" +
				" users" +
			" WHERE" +
				" status = '1' AND email = #{email}")
	@Results(
		value = {
				@Result(property = "id", column = "id"),
				@Result(property = "roles", column = "id", many = @Many(select = "findRolesById"))
		}
	)
	public User findUserByEmail(@Param("email") String email);
	
}
