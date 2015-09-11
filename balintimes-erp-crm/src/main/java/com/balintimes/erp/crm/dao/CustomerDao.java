package com.balintimes.erp.crm.dao;

import java.util.List;

import com.balintimes.erp.crm.model.Customer;

public interface CustomerDao {
	List<Customer> getCustomerList(String name, int startIndex, int pageSize);

	int getCountCustomerByList(String name);

	Customer getCustomer(int uid);
}
