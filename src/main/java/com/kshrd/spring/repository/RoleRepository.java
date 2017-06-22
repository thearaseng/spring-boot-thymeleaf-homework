package com.kshrd.spring.repository;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import com.kshrd.spring.model.Role;

@Repository
public interface RoleRepository {

	@Select("SELECT id, role_name FROM role")
	@Results(value={
			@Result(property="roleName" , column="role_name")
	})
	List<Role> getRoles();
	
	@Insert("INSERT INTO role (role_name) VALUES (#{role.roleName})")
	public boolean addRole(@Param("role") Role role);
	
}
