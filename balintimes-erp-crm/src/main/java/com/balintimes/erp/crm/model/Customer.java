package com.balintimes.erp.crm.model;

import java.io.Serializable;
import java.util.Date;

public class Customer implements Serializable {

	private static final long serialVersionUID = 6579735830063966491L;

	private int uid;
	private String name;
	private String businessType;
	private String businessTypeName;
	private String areaUid;
	private String areaName;
	private String areaCode;
	private String phone;
	private String companyAddress;
	private String postCode;
	private Integer parentUid;
	private boolean forbidden;
	private boolean isDeleted;
	private String createBy;
	private Date createTime;
	private String editBy;
	private Date editTime;
	private String remarks;

	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBusinessType() {
		return businessType;
	}

	public void setBusinessType(String businessType) {
		this.businessType = businessType;
	}

	public String getBusinessTypeName() {
		return businessTypeName;
	}

	public void setBusinessTypeName(String businessTypeName) {
		this.businessTypeName = businessTypeName;
	}

	public String getAreaUid() {
		return areaUid;
	}

	public void setAreaUid(String areaUid) {
		this.areaUid = areaUid;
	}

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	public String getAreaCode() {
		return areaCode;
	}

	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getCompanyAddress() {
		return companyAddress;
	}

	public void setCompanyAddress(String companyAddress) {
		this.companyAddress = companyAddress;
	}

	public String getPostCode() {
		return postCode;
	}

	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}

	public Integer getParentUid() {
		return parentUid;
	}

	public void setParentUid(Integer parentUid) {
		this.parentUid = parentUid;
	}

	public boolean isForbidden() {
		return forbidden;
	}

	public void setForbidden(boolean forbidden) {
		this.forbidden = forbidden;
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

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

}
