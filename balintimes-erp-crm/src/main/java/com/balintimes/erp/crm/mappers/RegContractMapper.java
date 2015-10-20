package com.balintimes.erp.crm.mappers;

import java.util.List;

import com.balintimes.erp.crm.model.RegContract;

public interface RegContractMapper {
	List<RegContract> getRegContractByCustomer(int customerUid);

	RegContract getRegContract(int uid);

	void createRegContract(RegContract regContract);

	void updateRegContractByDel(int uid);

	void updateRegContract(RegContract regContract);
}
