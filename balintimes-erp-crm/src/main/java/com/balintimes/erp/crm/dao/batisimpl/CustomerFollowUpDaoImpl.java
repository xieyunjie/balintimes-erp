package com.balintimes.erp.crm.dao.batisimpl;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.balintimes.erp.crm.dao.CustomerFollowUpDao;
import com.balintimes.erp.crm.mappers.CustomerFollowUpMapper;
import com.balintimes.erp.crm.model.CustomerFollowUp;

@Repository
public class CustomerFollowUpDaoImpl implements CustomerFollowUpDao {

	@Resource
	private CustomerFollowUpMapper customerFollowUpMapper;

	public CustomerFollowUp getCustomerFollowUp(int uid) {
		// TODO Auto-generated method stub
		return this.customerFollowUpMapper.getCustomerFollowUp(uid);
	}

	public void updateCustomerFollowUp(CustomerFollowUp customerFollowUp) {
		// TODO Auto-generated method stub
		this.customerFollowUpMapper.updateCustomerFollowUp(customerFollowUp);
	}

	public void createCustomerFollowUp(CustomerFollowUp customerFollowUp) {
		// TODO Auto-generated method stub
		this.customerFollowUpMapper.createCustomerFollowUp(customerFollowUp);
	}

	public void updateCustomerFollowUpByDel(int uid) {
		// TODO Auto-generated method stub
		this.customerFollowUpMapper.updateCustomerFollowUpByDel(uid);
	}
}
