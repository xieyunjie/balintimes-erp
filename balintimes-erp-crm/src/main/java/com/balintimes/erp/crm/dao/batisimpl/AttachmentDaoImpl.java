package com.balintimes.erp.crm.dao.batisimpl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.balintimes.erp.crm.dao.AttachmentDao;
import com.balintimes.erp.crm.mappers.AttachmentMapper;
import com.balintimes.erp.crm.model.Attachment;

@Repository
public class AttachmentDaoImpl implements AttachmentDao {

	@Resource
	private AttachmentMapper attachmentMapper;

	public List<Attachment> getAttachmentByCustomer(int followUpUid) {
		// TODO Auto-generated method stub
		return this.attachmentMapper.getAttachmentByCustomer(followUpUid);
	}

	public Attachment getAttachment(int uid) {
		// TODO Auto-generated method stub
		return this.attachmentMapper.getAttachment(uid);
	}

	public void createAttachment(Attachment attachment) {
		// TODO Auto-generated method stub
		this.attachmentMapper.createAttachment(attachment);
	}

	public void deleteAttachment(int uid) {
		// TODO Auto-generated method stub
		this.attachmentMapper.deleteAttachment(uid);
	}

}
