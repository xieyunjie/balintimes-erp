package com.balintimes.erp.crm.model;

import java.io.Serializable;

public class Media implements Serializable {

	private static final long serialVersionUID = 6129471578968583253L;

	private String uid;
	private String name;
	private int protection;

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getProtection() {
		return protection;
	}

	public void setProtection(int protection) {
		this.protection = protection;
	}

}
