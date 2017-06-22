package com.kshrd.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import com.kshrd.spring.model.Role;
import com.kshrd.spring.model.User;
import com.kshrd.spring.service.RoleService;
import com.kshrd.spring.service.UserService;

@Controller
public class AdminController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private RoleService roleService;
	
	@RequestMapping("/admin/dashboard")
	public String dashboard(){
		return "admin/dashboard";
	}
	
	@RequestMapping("/admin/user-list")
	public String userList(Model model){
		model.addAttribute("USERS", userService.getUsers());
		return "/admin/user-list";
	}
	
	@RequestMapping("/admin/user-cu")
	public String userCU(Model model){
		model.addAttribute("USER", new User());
//		model.addAttribute("ROLES", userService.getRoles());
		return "/admin/user-cu";
	}
	
	@RequestMapping("/admin/user/{userHash}/detail")
	public String userDetail(@PathVariable String userHash, Model model){
		model.addAttribute("USER", userService.getUserByHash(userHash));
		return "admin/user-detail";
	}
	
	@RequestMapping(value="/api/user/create", method=RequestMethod.POST)
	public ModelAndView userCreate(@ModelAttribute User user){
		userService.addUser(user);
		return new ModelAndView("redirect:/admin/user-list");
	}
	
	@RequestMapping("/admin/role-list")
	public String roleList(Model model){
		model.addAttribute("ROLES", roleService.getRoles());
		return "admin/role-list";
	}
	
	@RequestMapping("/admin/role-cu")
	public String roleCU(Model model){
		model.addAttribute("ROLE", new Role());
		return "admin/role-cu";
	}
	
	@RequestMapping(value="/api/role/create", method=RequestMethod.POST)
	@ResponseBody
	public Role userCreate(@ModelAttribute Role role){
		return role;
	}
	
}
