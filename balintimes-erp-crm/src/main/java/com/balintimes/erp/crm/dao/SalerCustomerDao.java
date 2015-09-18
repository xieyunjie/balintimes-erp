package com.balintimes.erp.crm.dao;

import com.balintimes.erp.crm.model.SalerCustomer;

public interface SalerCustomerDao {
	SalerCustomer getSalerCustomer(int uid);

	void createSalerCustomer(SalerCustomer salerCustomer);

	void updateSalerCustomer(SalerCustomer salerCustomer);

	void updateSalerCustomerByForbidden(int uid);

	void updateSalerCustomerByDel(int uid);

	void deleteSalerCustomer(int uid);
}
