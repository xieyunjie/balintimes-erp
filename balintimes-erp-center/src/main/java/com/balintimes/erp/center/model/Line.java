package com.balintimes.erp.center.model;

import java.io.Serializable;
import java.util.Date;

public class Line implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -4935408337811905947L;
	private String uid;
	private int id;
	private String  kid;
	private String name;
	private String startstationuid;
	private String endstationuid;
	private Boolean deleted;
	private String comment;
	private Date edittime;
	private String editorname;
	private String editorid;
	private Date createtime;
	private String creatorname;
	private String creatorid;
	private Boolean isusebymediapool;
	private int priorityno;
	
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
	
	public String getStartstationuid() {
		return startstationuid;
	}
	public void setStartstationuid(String startstationuid) {
		this.startstationuid = startstationuid;
	}
	
	public String getEndstationuid() {
		return endstationuid;
	}
	public void setEndstationuid(String endstationuid) {
		this.endstationuid = endstationuid;
	}
	
	public Boolean getDeleted() {
		return deleted;
	}
	public void setDeleted(Boolean deleted) {
		this.deleted = deleted;
	}
	
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	
	public void setEdittime(Date edittime) {
		this.edittime = edittime;
	}
	public Date getEdittime() {
		return edittime;
	}
	
	public void setEditoname(String editorname) {
		this.editorname = editorname;
	}
	public String getEditoname() {
		return editorname;
	}
	
	public void setEditorid(String editorid) {
		this.editorid = editorid;
	}
	public String getEditorid() {
		return editorid;
	}
	
	public Date getCreatetime() {
		return createtime;
	}
	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}
	
	public void setCreatorname(String creatorname) {
		this.creatorname = creatorname;
	}
	public String getCreatorname() {
		return creatorname;
	}
	
	public void setCreatorid(String creatorid) {
		this.creatorid = creatorid;
	}
	public String getCreatorid() {
		return creatorid;
	}
	
	public void setIsusebymediapool(Boolean isusebymediapool) {
		this.isusebymediapool = isusebymediapool;
	}
	public Boolean getIsusebymediapool() {
		return isusebymediapool;
	}
	
	public int getPriorityno() {
		return priorityno;
	}
	public void setPriorityno(int priorityno) {
		this.priorityno = priorityno;
	}

}
