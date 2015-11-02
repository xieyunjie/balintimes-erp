package com.balintimes.erp.center.model;

import java.io.Serializable;
import java.util.Date;

public class Station implements Serializable{
    private String uid ;
    private int id;
    private String  kid;
    private String  name;
    private String leveluid;
    private boolean transit;
    private boolean deleted;
    private String comment;
    private String creatorid;
    private String creatorname;
    private Date createtime;
    private String editorid;
    private String editorname;
    private Date edittime;
    private String lineuid;
    private int stationorder;
    private String cityuid;
	private String levelname;

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getKid() {
		return kid;
	}

	public void setKid(String kid) {
		this.kid = kid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLeveluid() {
		return leveluid;
	}

	public void setLeveluid(String leveluid) {
		this.leveluid = leveluid;
	}

	public boolean isTransit() {
		return transit;
	}

	public void setTransit(boolean transit) {
		this.transit = transit;
	}

	public boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getCreatorid() {
		return creatorid;
	}

	public void setCreatorid(String creatorid) {
		this.creatorid = creatorid;
	}

	public String getCreatorname() {
		return creatorname;
	}

	public void setCreatorname(String creatorname) {
		this.creatorname = creatorname;
	}

	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	public String getEditorid() {
		return editorid;
	}

	public void setEditorid(String editorid) {
		this.editorid = editorid;
	}

	public String getEditorname() {
		return editorname;
	}

	public void setEditorname(String editorname) {
		this.editorname = editorname;
	}

	public Date getEdittime() {
		return edittime;
	}

	public void setEdittime(Date edittime) {
		this.edittime = edittime;
	}

	public String getLineuid() {
		return lineuid;
	}

	public void setLineuid(String lineuid) {
		this.lineuid = lineuid;
	}

	public int getStationorder() {
		return stationorder;
	}

	public void setStationorder(int stationorder) {
		this.stationorder = stationorder;
	}

	public String getCityuid() {
		return cityuid;
	}

	public void setCityuid(String cityuid) {
		this.cityuid = cityuid;
	}

	public String getLevelname() {
		return levelname;
	}

	public void setLevelname(String levelname) {
		this.levelname = levelname;
	}
}