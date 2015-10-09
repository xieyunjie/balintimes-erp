package com.balintimes.erp.crm.model;

import java.io.Serializable;
import java.util.Date;

public class CustomerFollowUp implements Serializable {

	private static final long serialVersionUID = 226061312403593264L;

	private int uid;
	private int customerUid;
	private String customerCategoryUid;
	private String customerCategoryName;
	private String proxyCompany;
	private String brand;
	private int state;
	private String stateName;
	private String userUid;
	private boolean forbidden;
	private boolean isDeleted;
	private Date createTime;
	private Integer regCustomerUid;

	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

	public int getCustomerUid() {
		return customerUid;
	}

	public void setCustomerUid(int customerUid) {
		this.customerUid = customerUid;
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

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
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

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Integer getRegCustomerUid() {
		return regCustomerUid;
	}

	public void setRegCustomerUid(Integer regCustomerUid) {
		this.regCustomerUid = regCustomerUid;
	}

}
