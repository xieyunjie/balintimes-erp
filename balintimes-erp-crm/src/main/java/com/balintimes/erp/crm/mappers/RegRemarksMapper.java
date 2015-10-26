package com.balintimes.erp.crm.mappers;

import java.util.List;

import com.balintimes.erp.crm.model.RegRemarks;

public interface RegRemarksMapper {
	List<RegRemarks> getRegRemarksByCustomer(int customerUid);

	RegRemarks getRegRemarks(int uid);

	void createRegRemarks(RegRemarks regRemarks);

	void updateRegRemarksByDel(int uid);

	void updateRegRemarks(RegRemarks regRemarks);
}
