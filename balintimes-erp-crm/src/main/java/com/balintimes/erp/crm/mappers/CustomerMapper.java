package com.balintimes.erp.crm.mappers;

import java.util.List;
import java.util.Map;

import com.balintimes.erp.crm.model.Customer;

public interface CustomerMapper {
	List<Customer> getCustomerList(Map<String, Object> map);

	int getCountCustomerByList(Map<String, Object> map);

	Customer getCustomer(int uid);
}
