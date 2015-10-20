package com.balintimes.erp.crm.mappers;

import java.util.List;

import com.balintimes.erp.crm.model.Attachment;

public interface AttachmentMapper {
	List<Attachment> getAttachmentByCustomer(int followUpUid);

	Attachment getAttachment(int uid);

	void createAttachment(Attachment attachment);

	void deleteAttachment(int uid);
}
