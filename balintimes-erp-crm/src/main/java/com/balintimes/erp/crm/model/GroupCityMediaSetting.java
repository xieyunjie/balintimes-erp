package com.balintimes.erp.crm.model;

import java.io.Serializable;

public class GroupCityMediaSetting implements Serializable {

	private static final long serialVersionUID = -3069737857647195514L;

	private String uid;
	private String groupUid;
	private String cityMediaUid;
	private String cityUid;
	private String mediaUid;

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getGroupUid() {
		return groupUid;
	}

	public void setGroupUid(String groupUid) {
		this.groupUid = groupUid;
	}

	public String getCityMediaUid() {
		return cityMediaUid;
	}

	public void setCityMediaUid(String cityMediaUid) {
		this.cityMediaUid = cityMediaUid;
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

}
