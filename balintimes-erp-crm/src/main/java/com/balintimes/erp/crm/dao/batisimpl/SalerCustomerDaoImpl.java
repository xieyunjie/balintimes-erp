package com.balintimes.erp.crm.dao.batisimpl;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.balintimes.erp.crm.dao.SalerCustomerDao;
import com.balintimes.erp.crm.mappers.SalerCustomerMapper;
import com.balintimes.erp.crm.model.SalerCustomer;

@Repository
public class SalerCustomerDaoImpl implements SalerCustomerDao {

	@Resource
	private SalerCustomerMapper salerCustomerMapper;

	public SalerCustomer getSalerCustomer(int uid) {
		// TODO Auto-generated method stub
		return this.salerCustomerMapper.getSalerCustomer(uid);
	}

	public void createSalerCustomer(SalerCustomer salerCustomer) {
		// TODO Auto-generated method stub
		this.salerCustomerMapper.createSalerCustomer(salerCustomer);
	}

	public void updateSalerCustomer(SalerCustomer salerCustomer) {
		// TODO Auto-generated method stub
		this.salerCustomerMapper.updateSalerCustomer(salerCustomer);
	}

	public void updateSalerCustomerByForbidden(int uid) {
		// TODO Auto-generated method stub
		this.salerCustomerMapper.updateSalerCustomerByForbidden(uid);
	}

	public void updateSalerCustomerByDel(int uid) {
		// TODO Auto-generated method stub
		this.salerCustomerMapper.updateSalerCustomerByDel(uid);
	}

	public void deleteSalerCustomer(int uid) {
		// TODO Auto-generated method stub
		this.salerCustomerMapper.deleteSalerCustomer(uid);
	}

}
