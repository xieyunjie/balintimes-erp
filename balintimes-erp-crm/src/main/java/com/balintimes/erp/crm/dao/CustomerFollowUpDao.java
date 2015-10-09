package com.balintimes.erp.crm.dao;

import com.balintimes.erp.crm.model.CustomerFollowUp;

public interface CustomerFollowUpDao {
	CustomerFollowUp getCustomerFollowUp(int uid);

	void updateCustomerFollowUp(CustomerFollowUp customerFollowUp);

	void createCustomerFollowUp(CustomerFollowUp customerFollowUp);

	void updateCustomerFollowUpByDel(int uid);
}
