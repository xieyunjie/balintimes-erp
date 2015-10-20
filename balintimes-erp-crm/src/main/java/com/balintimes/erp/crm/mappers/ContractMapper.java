package com.balintimes.erp.crm.mappers;

import java.util.List;

import com.balintimes.erp.crm.model.Contract;

public interface ContractMapper {
	List<Contract> getContractByFollowUp(int followUpUid);

	Contract getContract(int uid);

	void createContract(Contract contract);

	void updateContractByDel(int uid);

	void updateContract(Contract contract);
}
