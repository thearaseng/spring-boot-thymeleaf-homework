package com.kshrd.spring.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kshrd.spring.entity.Role;
import com.kshrd.spring.repository.RoleRepository;
import com.kshrd.spring.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService {
	
	@Autowired
	private RoleRepository roleRepository;

    @Override
    public List<Role> findAllRole() {
        return roleRepository.findAllRole();
    }
	
}
