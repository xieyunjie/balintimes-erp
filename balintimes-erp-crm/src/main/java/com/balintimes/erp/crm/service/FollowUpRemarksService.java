package com.balintimes.erp.crm.service;

import com.balintimes.erp.crm.model.RemarksInfo;

public interface FollowUpRemarksService {

	RemarksInfo getRemark(int uid, boolean isReg);

	void createRemarks(RemarksInfo remarks);

	void deleteRemark(int uid, boolean isReg);

	void updateRemark(RemarksInfo remarks);
}
