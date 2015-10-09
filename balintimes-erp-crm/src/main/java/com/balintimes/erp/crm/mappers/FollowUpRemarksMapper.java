package com.balintimes.erp.crm.mappers;

import com.balintimes.erp.crm.model.FollowUpRemarks;

public interface FollowUpRemarksMapper {
	FollowUpRemarks getFollowUpRemarks(int uid);

	void createFollowUpRemarks(FollowUpRemarks followUpRemarks);

	void updateFollowUpRemarksByDel(int uid);
	
	void updateFollowUpRemarks(FollowUpRemarks followUpRemarks);
}
