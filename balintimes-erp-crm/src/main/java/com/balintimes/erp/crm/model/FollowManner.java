package com.balintimes.erp.crm.model;

import java.io.Serializable;

public class FollowManner implements Serializable {

	private static final long serialVersionUID = -323138871725527483L;

	private int id;
	private String name;

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
}
