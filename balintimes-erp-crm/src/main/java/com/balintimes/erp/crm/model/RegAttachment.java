package com.balintimes.erp.crm.model;

import java.io.Serializable;
import java.util.Date;

public class RegAttachment implements Serializable {

	private static final long serialVersionUID = 5461227144756422354L;

	private int uid;
	private int customerUid;
	private String customerName;
	private String remarks;
	private String url;
	private String createBy;
	private Date createTime;
	private String fileName;
	private String userUid;

	
	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

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

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
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

	public String getUserUid() {
		return userUid;
	}

	public void setUserUid(String userUid) {
		this.userUid = userUid;
	}

}
