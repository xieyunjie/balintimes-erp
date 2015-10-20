package com.balintimes.erp.crm.service;

import java.util.List;

import com.balintimes.erp.crm.model.AttachmentInfo;

public interface AttachmentService {
	List<AttachmentInfo> getAttachmentInfoList(int objUid, boolean isReg);

	AttachmentInfo getAttachmentInfo(int uid, boolean isReg);

	void createAttachmentInfo(AttachmentInfo attachment);

	void deleteAttachmentInfo(int uid, boolean isReg);
}
