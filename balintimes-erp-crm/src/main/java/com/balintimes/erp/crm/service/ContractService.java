package com.balintimes.erp.crm.service;

import java.util.List;

import com.balintimes.erp.crm.model.ContractInfo;

public interface ContractService {
	List<ContractInfo> getContractListByObj(int objUid, boolean isReg);

	ContractInfo getContractInfo(int uid, boolean isReg);

	void createContractInfo(ContractInfo contract);

	void updateContractInfo(ContractInfo contract);

	void deleteContractInfo(int uid, boolean isReg);
}
