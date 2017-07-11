package com.kshrd.spring.repository;


import com.kshrd.spring.entity.Role;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleRepository {

	@Select("SELECT id, name, remark, status, uuid FROM roles")
    public List<Role> findAllRole();
	
}
