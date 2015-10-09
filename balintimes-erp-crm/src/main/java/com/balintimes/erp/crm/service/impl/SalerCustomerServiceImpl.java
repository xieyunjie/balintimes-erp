package com.balintimes.erp.crm.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.balintimes.erp.crm.dao.SalerCustomerDao;
import com.balintimes.erp.crm.model.SalerCustomer;
import com.balintimes.erp.crm.service.SalerCustomerService;

@Service
public class SalerCustomerServiceImpl implements SalerCustomerService {

	@Resource
	private SalerCustomerDao salerCustomerDao;
	
	public SalerCustomer getSalerCustomer(int uid) {
		// TODO Auto-generated method stub
		return this.salerCustomerDao.getSalerCustomer(uid);
	}

	public void createSalerCustomer(SalerCustomer salerCustomer) {
		// TODO Auto-generated method stub
		this.salerCustomerDao.createSalerCustomer(salerCustomer);
	}

	public void updateSalerCustomer(SalerCustomer salerCustomer) {
		// TODO Auto-generated method stub
		this.salerCustomerDao.updateSalerCustomer(salerCustomer);
	}

	public void updateSalerCustomerByForbidden(int uid) {
		// TODO Auto-generated method stub
		this.salerCustomerDao.updateSalerCustomerByForbidden(uid);
	}

	public void updateSalerCustomerByDel(int uid) {
		// TODO Auto-generated method stub
		this.salerCustomerDao.updateSalerCustomerByDel(uid);
	}

	public void deleteSalerCustomer(int uid) {
		// TODO Auto-generated method stub
		this.salerCustomerDao.deleteSalerCustomer(uid);
	}

}
