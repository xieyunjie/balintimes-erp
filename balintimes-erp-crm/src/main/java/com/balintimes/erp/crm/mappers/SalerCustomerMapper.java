package com.balintimes.erp.crm.mappers;

import com.balintimes.erp.crm.model.SalerCustomer;

public interface SalerCustomerMapper {
	SalerCustomer getSalerCustomer(int uid);

	void createSalerCustomer(SalerCustomer salerCustomer);

	void updateSalerCustomer(SalerCustomer salerCustomer);

	void updateSalerCustomerByForbidden(int uid);

	void updateSalerCustomerByDel(int uid);

	void deleteSalerCustomer(int uid);
}
