package com.balintimes.erp.center.model;

import java.io.Serializable;

public class RoleType implements Serializable {

	private static final long serialVersionUID = -1553082835811135142L;

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
