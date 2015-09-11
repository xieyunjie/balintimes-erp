package com.balintimes.erp.center.service.base;

import java.util.List;

import com.balintimes.erp.center.model.base.CustomerCategory;

public interface CustomerCategoryService {
	List<CustomerCategory> GetCustomerCategoryList(String name);

	CustomerCategory GetCustomerCategory(String uid);

	void CreateCustomerCategory(CustomerCategory customerCategory);

	void UpdateCustomerCategory(CustomerCategory customerCategory);

	void DeleteCustomerCategory(String uid);
}
