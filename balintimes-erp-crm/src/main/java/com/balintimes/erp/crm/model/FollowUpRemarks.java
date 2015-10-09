package com.balintimes.erp.crm.model;

import java.io.Serializable;
import java.util.Date;

public class FollowUpRemarks implements Serializable {

	private static final long serialVersionUID = -8782561510155188888L;

	private int uid;
	private int followUpUid;
	private int customerUid;
	private String customerName;
	private String userUid;
	private String contract;
	private int mannerUid;
	private String mannerName;
	private String phone;
	private String remarks;
	private Date followUpDate;
	private String summary;
	private String persons;
	private boolean isDeleted;
	private String createBy;
	private Date createTime;
	private String editBy;
	private Date editTime;

	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

	public int getFollowUpUid() {
		return followUpUid;
	}

	public void setFollowUpUid(int followUpUid) {
		this.followUpUid = followUpUid;
	}

	public int getCustomerUid() {
		return customerUid;
	}

	public void setCustomerUid(int customerUid) {
		this.customerUid = customerUid;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getUserUid() {
		return userUid;
	}

	public void setUserUid(String userUid) {
		this.userUid = userUid;
	}

	public String getContract() {
		return contract;
	}

	public void setContract(String contract) {
		this.contract = contract;
	}

	public int getMannerUid() {
		return mannerUid;
	}

	public void setMannerUid(int mannerUid) {
		this.mannerUid = mannerUid;
	}

	public String getMannerName() {
		return mannerName;
	}

	public void setMannerName(String mannerName) {
		this.mannerName = mannerName;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public Date getFollowUpDate() {
		return followUpDate;
	}

	public void setFollowUpDate(Date followUpDate) {
		this.followUpDate = followUpDate;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getPersons() {
		return persons;
	}

	public void setPersons(String persons) {
		this.persons = persons;
	}

	public boolean isDeleted() {
		return isDeleted;
	}

	public void setDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	public String getCreateBy() {
		return createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getEditBy() {
		return editBy;
	}

	public void setEditBy(String editBy) {
		this.editBy = editBy;
	}

	public Date getEditTime() {
		return editTime;
	}

	public void setEditTime(Date editTime) {
		this.editTime = editTime;
	}

}
