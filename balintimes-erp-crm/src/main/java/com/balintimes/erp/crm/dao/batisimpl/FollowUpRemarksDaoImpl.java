package com.balintimes.erp.crm.dao.batisimpl;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.balintimes.erp.crm.dao.FollowUpRemarksDao;
import com.balintimes.erp.crm.mappers.FollowUpRemarksMapper;
import com.balintimes.erp.crm.model.FollowUpRemarks;

@Repository
public class FollowUpRemarksDaoImpl implements FollowUpRemarksDao {

	@Resource
	private FollowUpRemarksMapper followUpRemarksMapper;

	public FollowUpRemarks getFollowUpRemarks(int uid) {
		// TODO Auto-generated method stub
		return this.followUpRemarksMapper.getFollowUpRemarks(uid);
	}

	public void createFollowUpRemarks(FollowUpRemarks followUpRemarks) {
		// TODO Auto-generated method stub
		this.followUpRemarksMapper.createFollowUpRemarks(followUpRemarks);
	}

	public void updateFollowUpRemarksByDel(int uid) {
		// TODO Auto-generated method stub
		this.followUpRemarksMapper.updateFollowUpRemarksByDel(uid);
	}

	public void updateFollowUpRemarks(FollowUpRemarks followUpRemarks) {
		// TODO Auto-generated method stub
		this.followUpRemarksMapper.updateFollowUpRemarks(followUpRemarks);
	}

}
