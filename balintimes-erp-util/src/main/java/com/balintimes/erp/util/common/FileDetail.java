package com.balintimes.erp.util.common;

import java.io.Serializable;

public class FileDetail implements Serializable {

	private static final long serialVersionUID = -3829620446135313201L;

	private String uid;
	private String fileName;
	private String prx;
	private String fileFullName;
	
	private String baseUrl;

	public String getBaseUrl() {
		return baseUrl;
	}

	public void setBaseUrl(String baseUrl) {
		this.baseUrl = baseUrl;
	}

	public String getFileFullName() {
		return fileFullName;
	}

	public void setFileFullName(String fileFullName) {
		this.fileFullName = fileFullName;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getPrx() {
		return prx;
	}

	public void setPrx(String prx) {
		this.prx = prx;
	}

}
