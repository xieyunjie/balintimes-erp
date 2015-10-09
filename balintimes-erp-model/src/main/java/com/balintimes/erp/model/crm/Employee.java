package com.balintimes.erp.model.crm;

import java.util.Date;

public class Employee {
	private String uid;
	private String username;
	private boolean isadmin;
	private String employeename;
	private String usertypename;
	private String email;
	private Date lastlogin;
	private String sex;
	private String avatarurl;
	private int rootdeep = 0;
	
	
	public Object getPosts() {
		return posts;
	}

	public void setPosts(Object posts) {
		this.posts = posts;
	}

	private Object posts;
	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public boolean isIsadmin() {
		return isadmin;
	}

	public void setIsadmin(boolean isadmin) {
		this.isadmin = isadmin;
	}

	public String getEmployeename() {
		return employeename;
	}

	public void setEmployeename(String employeename) {
		this.employeename = employeename;
	}

	public String getUsertypename() {
		return usertypename;
	}

	public void setUsertypename(String usertypename) {
		this.usertypename = usertypename;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getLastlogin() {
		return lastlogin;
	}

	public void setLastlogin(Date lastlogin) {
		this.lastlogin = lastlogin;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getAvatarurl() {
		return avatarurl;
	}

	public void setAvatarurl(String avatarurl) {
		this.avatarurl = avatarurl;
	}

	public int getRootdeep() {
		return rootdeep;
	}

	public void setRootdeep(int rootdeep) {
		this.rootdeep = rootdeep;
	}
}
