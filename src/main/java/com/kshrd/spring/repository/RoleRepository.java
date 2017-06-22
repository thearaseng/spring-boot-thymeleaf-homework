package com.kshrd.spring.repository;

import java.util.List;

import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import com.kshrd.spring.model.Role;
import com.kshrd.spring.model.User;

@Repository
public interface RoleRepository {

	@Select("SELECT id, role_name FROM role")
	@Results(value={
			@Result(property="roleName" , column="role_name")
	})
	List<Role> getRoles();
	
}
