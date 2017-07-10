package com.kshrd.spring.repository;


import com.kshrd.spring.entity.Role;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleRepository {

	@Insert("<script>INSERT INTO user_roles" +
			" VALUES" +
			" <foreach collection = 'roles' item='role' separator=','>" +
			" (#{userId}, #{role.id})" +
			" </foreach>" +
			"</script>")
	public boolean insertUserRole(@Param("roles") List<Role> roles, @Param("userId") int userId);
	
	@Delete("DELETE FROM user_roles WHERE user_id = (SELECT id FROM users WHERE uuid = #{uuid})")
	public boolean deleteUserRoleByUserId(@Param("uuid") String uuid);
	
}
