package com.balintimes.erp.crm.dao;

import java.util.List;

import com.balintimes.erp.crm.model.Attachment;

public interface AttachmentDao {
	List<Attachment> getAttachmentByCustomer(int followUpUid);

	Attachment getAttachment(int uid);

	void createAttachment(Attachment attachment);

	void deleteAttachment(int uid);
}
