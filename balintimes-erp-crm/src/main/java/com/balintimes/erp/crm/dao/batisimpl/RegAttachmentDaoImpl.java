package com.balintimes.erp.crm.dao.batisimpl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.balintimes.erp.crm.dao.RegAttachmentDao;
import com.balintimes.erp.crm.mappers.RegAttachmentMapper;
import com.balintimes.erp.crm.model.RegAttachment;

@Repository
public class RegAttachmentDaoImpl implements RegAttachmentDao {

	@Resource
	private RegAttachmentMapper regAttachmentMapper;

	public List<RegAttachment> getRegAttachmentByCustomer(int customerUid) {
		// TODO Auto-generated method stub
		return this.regAttachmentMapper.getRegAttachmentByCustomer(customerUid);
	}

	public RegAttachment getRegAttachment(int uid) {
		// TODO Auto-generated method stub
		return this.regAttachmentMapper.getRegAttachment(uid);
	}

	public void createRegAttachment(RegAttachment regAttachment) {
		// TODO Auto-generated method stub
		this.regAttachmentMapper.createRegAttachment(regAttachment);
	}

	public void deleteRegAttachment(int uid) {
		// TODO Auto-generated method stub
		this.regAttachmentMapper.deleteRegAttachment(uid);
	}

}
