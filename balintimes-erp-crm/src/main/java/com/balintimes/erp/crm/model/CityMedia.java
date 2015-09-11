package com.balintimes.erp.crm.model;

import java.io.Serializable;

public class CityMedia implements Serializable {

	private static final long serialVersionUID = -2751409066174290368L;

	private String uid;
	private String cityUid;
	private String mediaUid;
	private int protection;

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getCityUid() {
		return cityUid;
	}

	public void setCityUid(String cityUid) {
		this.cityUid = cityUid;
	}

	public String getMediaUid() {
		return mediaUid;
	}

	public void setMediaUid(String mediaUid) {
		this.mediaUid = mediaUid;
	}

	public int getProtection() {
		return protection;
	}

	public void setProtection(int protection) {
		this.protection = protection;
	}

}
