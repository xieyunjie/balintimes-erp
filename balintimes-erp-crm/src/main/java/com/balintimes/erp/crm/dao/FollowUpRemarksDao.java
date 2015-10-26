package com.balintimes.erp.crm.dao;

import java.util.List;

import com.balintimes.erp.crm.model.FollowUpRemarks;

public interface FollowUpRemarksDao {
	List<FollowUpRemarks> getFollowUpRemarksByFollow(int followUpUid);

	FollowUpRemarks getFollowUpRemarks(int uid);

	void createFollowUpRemarks(FollowUpRemarks followUpRemarks);

	void updateFollowUpRemarksByDel(int uid);

	void updateFollowUpRemarks(FollowUpRemarks followUpRemarks);
}
