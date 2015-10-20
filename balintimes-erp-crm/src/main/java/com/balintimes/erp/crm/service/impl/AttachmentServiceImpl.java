package com.balintimes.erp.crm.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.balintimes.erp.crm.dao.AttachmentDao;
import com.balintimes.erp.crm.dao.RegAttachmentDao;
import com.balintimes.erp.crm.model.Attachment;
import com.balintimes.erp.crm.model.AttachmentInfo;
import com.balintimes.erp.crm.model.RegAttachment;
import com.balintimes.erp.crm.service.AttachmentService;

@Service
public class AttachmentServiceImpl implements AttachmentService {

	@Resource
	private RegAttachmentDao regAttachmentDao;
	@Resource
	private AttachmentDao attachmentDao;

	public List<AttachmentInfo> getAttachmentInfoList(int objUid, boolean isReg) {
		// TODO Auto-generated method stub
		List<AttachmentInfo> list = new ArrayList<AttachmentInfo>();
		if (isReg) {
			List<Attachment> atts = this.attachmentDao
					.getAttachmentByCustomer(objUid);
			for (Attachment item : atts) {
				AttachmentInfo att = new AttachmentInfo();
				att.setUid(item.getUid());
				att.setFollowUpUid(item.getFollowUpUid());
				att.setCustomerUid(item.getCustomerUid());
				att.setCustomerName(item.getCustomerName());
				att.setRemarks(item.getRemarks());
				att.setUrl(item.getUrl());
				att.setCreateBy(item.getCreateBy());
				att.setCreateTime(item.getCreateTime());
				att.setUserUid(item.getUserUid());
				att.setReg(true);
				att.setFileName(item.getFileName());

				list.add(att);
			}

		} else {
			List<RegAttachment> atts = this.regAttachmentDao
					.getRegAttachmentByCustomer(objUid);
			for (RegAttachment item : atts) {
				AttachmentInfo att = new AttachmentInfo();
				att.setUid(item.getUid());
				att.setFollowUpUid(-1);
				att.setCustomerUid(item.getCustomerUid());
				att.setCustomerName(item.getCustomerName());
				att.setRemarks(item.getRemarks());
				att.setUrl(item.getUrl());
				att.setCreateBy(item.getCreateBy());
				att.setCreateTime(item.getCreateTime());
				att.setUserUid(item.getUserUid());
				att.setFileName(item.getFileName());
				att.setReg(false);

				list.add(att);
			}
		}

		return list;
	}

	public AttachmentInfo getAttachmentInfo(int uid, boolean isReg) {
		// TODO Auto-generated method stub
		AttachmentInfo att = new AttachmentInfo();
		if (isReg) {
			Attachment item = this.attachmentDao.getAttachment(uid);
			att.setUid(item.getUid());
			att.setFollowUpUid(item.getFollowUpUid());
			att.setCustomerUid(item.getCustomerUid());
			att.setCustomerName(item.getCustomerName());
			att.setRemarks(item.getRemarks());
			att.setUrl(item.getUrl());
			att.setCreateBy(item.getCreateBy());
			att.setCreateTime(item.getCreateTime());
			att.setUserUid(item.getUserUid());
			att.setFileName(item.getFileName());
			att.setReg(true);

		} else {
			RegAttachment item = this.regAttachmentDao.getRegAttachment(uid);
			att.setUid(item.getUid());
			att.setFollowUpUid(-1);
			att.setCustomerUid(item.getCustomerUid());
			att.setCustomerName(item.getCustomerName());
			att.setRemarks(item.getRemarks());
			att.setUrl(item.getUrl());
			att.setCreateBy(item.getCreateBy());
			att.setCreateTime(item.getCreateTime());
			att.setUserUid(item.getUserUid());
			att.setFileName(item.getFileName());
			att.setReg(false);
		}
		return att;
	}

	public void createAttachmentInfo(AttachmentInfo attachment) {
		// TODO Auto-generated method stub
		if (attachment.isReg()) {
			Attachment att = new Attachment();
			att.setUid(attachment.getUid());
			att.setFollowUpUid(attachment.getFollowUpUid());
			att.setCustomerUid(attachment.getCustomerUid());
			att.setCustomerName(attachment.getCustomerName());
			att.setRemarks(attachment.getRemarks());
			att.setUrl(attachment.getUrl());
			att.setCreateBy(attachment.getCreateBy());
			att.setCreateTime(attachment.getCreateTime());
			att.setUserUid(attachment.getUserUid());
			att.setFileName(attachment.getFileName());

			this.attachmentDao.createAttachment(att);
		} else {
			RegAttachment att = new RegAttachment();
			att.setUid(attachment.getUid());
			att.setCustomerUid(attachment.getCustomerUid());
			att.setCustomerName(attachment.getCustomerName());
			att.setRemarks(attachment.getRemarks());
			att.setUrl(attachment.getUrl());
			att.setCreateBy(attachment.getCreateBy());
			att.setCreateTime(attachment.getCreateTime());
			att.setUserUid(attachment.getUserUid());
			att.setFileName(attachment.getFileName());

			this.regAttachmentDao.createRegAttachment(att);
		}
	}

	public void deleteAttachmentInfo(int uid, boolean isReg) {
		// TODO Auto-generated method stub
		if (isReg) {
			this.attachmentDao.deleteAttachment(uid);
		} else {
			this.regAttachmentDao.deleteRegAttachment(uid);
		}
	}

}
