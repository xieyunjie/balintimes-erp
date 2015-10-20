package com.balintimes.erp.crm.dao.batisimpl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.balintimes.erp.crm.dao.ContractDao;
import com.balintimes.erp.crm.mappers.ContractMapper;
import com.balintimes.erp.crm.model.Contract;

@Repository
public class ContractDaoImpl implements ContractDao {

	@Resource
	private ContractMapper contractMapper;

	public List<Contract> getContractByFollowUp(int followUpUid) {
		// TODO Auto-generated method stub
		return this.contractMapper.getContractByFollowUp(followUpUid);
	}

	public Contract getContract(int uid) {
		// TODO Auto-generated method stub
		return this.contractMapper.getContract(uid);
	}

	public void createContract(Contract contract) {
		// TODO Auto-generated method stub
		this.contractMapper.createContract(contract);
	}

	public void updateContractByDel(int uid) {
		// TODO Auto-generated method stub
		this.contractMapper.updateContractByDel(uid);
	}

	public void updateContract(Contract contract) {
		// TODO Auto-generated method stub
		this.contractMapper.updateContract(contract);
	}

}
