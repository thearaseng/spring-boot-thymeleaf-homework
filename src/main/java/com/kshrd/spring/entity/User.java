 package com.kshrd.spring.entity;

import java.util.Date;
import java.util.List;

/**
 * 
 * @author Tola
 *	Created Date: 2017/06/27
 */
public class User{

	private int id;
	private String username;
	private String email;
	private String password;
	private Date dob;
	private String gender;
	private String device;
	private String uuid;
	private String remark;
	private String status;
	
	private List<Role> roles;

	public User() {}

	public User(int id, String username, String email, String password, Date dob, String gender,
				String device, String uuid, String remark, String status, List<Role> roles) {
		this.id = id;
		this.username = username;
		this.email = email;
		this.password = password;
		this.dob = dob;
		this.gender = gender;
		this.device = device;
		this.uuid = uuid;
		this.remark = remark;
		this.status = status;
		this.roles = roles;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getDevice() {
		return device;
	}

	public void setDevice(String device) {
		this.device = device;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	@Override
	public String toString() {
		return "User{" +
				"id=" + id +
				", username='" + username + '\'' +
				", email='" + email + '\'' +
				", password='" + password + '\'' +
				", dob=" + dob +
				", gender='" + gender + '\'' +
				", device='" + device + '\'' +
				", uuid='" + uuid + '\'' +
				", remark='" + remark + '\'' +
				", status='" + status + '\'' +
				", roles=" + roles +
				'}';
	}
}