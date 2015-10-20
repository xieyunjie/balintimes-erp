package com.balintimes.erp.crm.dao.batisimpl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.balintimes.erp.crm.dao.RegContractDao;
import com.balintimes.erp.crm.mappers.RegContractMapper;
import com.balintimes.erp.crm.model.RegContract;

@Repository
public class RegContractDaoImpl implements RegContractDao {

	@Resource
	private RegContractMapper regContractMapper;

	public List<RegContract> getRegContractByCustomer(int customerUid) {
		// TODO Auto-generated method stub
		return this.regContractMapper.getRegContractByCustomer(customerUid);
	}

	public RegContract getRegContract(int uid) {
		// TODO Auto-generated method stub
		return this.regContractMapper.getRegContract(uid);
	}

	public void createRegContract(RegContract regContract) {
		// TODO Auto-generated method stub
		this.regContractMapper.createRegContract(regContract);
	}

	public void updateRegContractByDel(int uid) {
		// TODO Auto-generated method stub
		this.regContractMapper.updateRegContractByDel(uid);
	}

	public void updateRegContract(RegContract regContract) {
		// TODO Auto-generated method stub
		this.regContractMapper.updateRegContract(regContract);
	}

}
