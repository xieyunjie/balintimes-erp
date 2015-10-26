package com.balintimes.erp.crm.service;

import java.util.List;

import com.balintimes.erp.crm.model.RemarksInfo;

public interface FollowUpRemarksService {
	List<RemarksInfo> getRemarksInfoByCustomer(int objUid, boolean isReg);

	RemarksInfo getRemark(int uid, boolean isReg);

	void createRemarks(RemarksInfo remarks);

	void deleteRemark(int uid, boolean isReg);

	void updateRemark(RemarksInfo remarks);
}
