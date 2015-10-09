package com.balintimes.erp.crm.model;

import java.io.Serializable;

public class EmpCustomer implements Serializable {

	private static final long serialVersionUID = -4429378154101334973L;

	private int id;
	private int uid;
	private String name;
	private String businessTypeUid;
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
	private String brand;
	private String customerCategoryUid;
	private String customerCategoryName;
	private String proxyCompany;
	private int state;
	private String stateName;
	private String userUid;
	private String userName;
	private boolean isReg;
	private Integer followUid;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

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

	public String getBusinessTypeUid() {
		return businessTypeUid;
	}

	public void setBusinessTypeUid(String businessTypeUid) {
		this.businessTypeUid = businessTypeUid;
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

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getCustomerCategoryUid() {
		return customerCategoryUid;
	}

	public void setCustomerCategoryUid(String customerCategoryUid) {
		this.customerCategoryUid = customerCategoryUid;
	}

	public String getCustomerCategoryName() {
		return customerCategoryName;
	}

	public void setCustomerCategoryName(String customerCategoryName) {
		this.customerCategoryName = customerCategoryName;
	}

	public String getProxyCompany() {
		return proxyCompany;
	}

	public void setProxyCompany(String proxyCompany) {
		this.proxyCompany = proxyCompany;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public String getStateName() {
		return stateName;
	}

	public void setStateName(String stateName) {
		this.stateName = stateName;
	}

	public String getUserUid() {
		return userUid;
	}

	public void setUserUid(String userUid) {
		this.userUid = userUid;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public boolean isReg() {
		return isReg;
	}

	public void setReg(boolean isReg) {
		this.isReg = isReg;
	}

	public Integer getFollowUid() {
		return followUid;
	}

	public void setFollowUid(Integer followUid) {
		this.followUid = followUid;
	}

}
