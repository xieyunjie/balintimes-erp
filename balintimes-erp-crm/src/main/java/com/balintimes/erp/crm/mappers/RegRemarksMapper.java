package com.balintimes.erp.crm.mappers;

import com.balintimes.erp.crm.model.RegRemarks;

public interface RegRemarksMapper {
	RegRemarks getRegRemarks(int uid);

	void createRegRemarks(RegRemarks regRemarks);

	void updateRegRemarksByDel(int uid);

	void updateRegRemarks(RegRemarks regRemarks);
}
