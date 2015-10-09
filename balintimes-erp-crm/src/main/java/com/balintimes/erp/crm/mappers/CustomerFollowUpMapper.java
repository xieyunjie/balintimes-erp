package com.balintimes.erp.crm.mappers;

import com.balintimes.erp.crm.model.CustomerFollowUp;

public interface CustomerFollowUpMapper {
	CustomerFollowUp getCustomerFollowUp(int uid);

	void updateCustomerFollowUp(CustomerFollowUp customerFollowUp);

	void createCustomerFollowUp(CustomerFollowUp customerFollowUp);
	
	void updateCustomerFollowUpByDel(int uid);
}
