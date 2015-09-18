package com.balintimes.erp.crm.service;

import java.util.List;

import com.balintimes.erp.model.crm.BusinessType;
import com.balintimes.erp.model.crm.CustomerCategory;
import com.balintimes.erp.model.crm.Employee;

public interface CenterWebService {
	List<BusinessType> getBusinessTypeList();

	List<CustomerCategory> getCustomerCategoryList();

	List<Employee> getSubordinatesByUser(String username);
}
