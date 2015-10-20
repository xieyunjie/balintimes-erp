package com.balintimes.erp.crm.dao;

import java.util.List;

import com.balintimes.erp.crm.model.RegContract;

public interface RegContractDao {
	List<RegContract> getRegContractByCustomer(int customerUid);

	RegContract getRegContract(int uid);

	void createRegContract(RegContract regContract);

	void updateRegContractByDel(int uid);

	void updateRegContract(RegContract regContract);

}
