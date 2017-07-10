package com.kshrd.spring.entity;

/**
 * 
 * @author Tola
 *	Created Date: 2017/10/27
 */
public class Role{

	private int id;
	private String name;
	private String remark;
	private String status;
	private String uuid;
	private int countUser;
	
	public Role() {}

	public Role(int id, String name, String remark, String status, String uuid, int countUser) {
		this.id = id;
		this.name = name;
		this.remark = remark;
		this.status = status;
		this.uuid = uuid;
		this.countUser = countUser;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public int getCountUser() {
		return countUser;
	}

	public void setCountUser(int countUser) {
		this.countUser = countUser;
	}

	@Override
	public String toString() {
		return "Role{" +
				"id=" + id +
				", name='" + name + '\'' +
				", remark='" + remark + '\'' +
				", status='" + status + '\'' +
				", uuid='" + uuid + '\'' +
				", countUser=" + countUser +
				'}';
	}
}
