package com.balintimes.erp.crm.dao;

import com.balintimes.erp.crm.model.RegRemarks;

public interface RegRemarksDao {
	RegRemarks getRegRemarks(int uid);

	void createRegRemarks(RegRemarks regRemarks);
	
	void updateRegRemarksByDel(int uid);
	
	void updateRegRemarks(RegRemarks regRemarks);
}
