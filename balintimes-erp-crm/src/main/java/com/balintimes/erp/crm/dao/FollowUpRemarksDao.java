package com.balintimes.erp.crm.dao;

import com.balintimes.erp.crm.model.FollowUpRemarks;

public interface FollowUpRemarksDao {
	FollowUpRemarks getFollowUpRemarks(int uid);

	void createFollowUpRemarks(FollowUpRemarks followUpRemarks);
	
	void updateFollowUpRemarksByDel(int uid);
	
	void updateFollowUpRemarks(FollowUpRemarks followUpRemarks);
}
