package com.balintimes.erp.center.service.impl.base;

import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.balintimes.erp.center.annotation.CustomerTransactional;
import com.balintimes.erp.center.dao.base.CustomerCategoryDao;
import com.balintimes.erp.center.model.base.CustomerCategory;
import com.balintimes.erp.center.service.base.CustomerCategoryService;

@Service
public class CustomerCategoryServiceImpl implements CustomerCategoryService {

	@Resource
	private CustomerCategoryDao customerCategoryDao;

	public List<CustomerCategory> GetCustomerCategoryList(String name) {
		// TODO Auto-generated method stub
		return this.customerCategoryDao.GetCustomerCategoryList(name);
	}

	public CustomerCategory GetCustomerCategory(String uid) {
		// TODO Auto-generated method stub
		return this.customerCategoryDao.GetCustomerCategory(uid);
	}

	@CustomerTransactional
	public void CreateCustomerCategory(CustomerCategory customerCategory) {
		// TODO Auto-generated method stub
		customerCategory.setUid(UUID.randomUUID().toString());
		this.customerCategoryDao.CreateCustomerCategory(customerCategory);
	}

	@CustomerTransactional
	public void UpdateCustomerCategory(CustomerCategory customerCategory) {
		// TODO Auto-generated method stub
		this.customerCategoryDao.UpdateCustomerCategory(customerCategory);
	}

	@CustomerTransactional
	public void DeleteCustomerCategory(String uid) {
		// TODO Auto-generated method stub
		this.customerCategoryDao.DeleteCustomerCategory(uid);
	}

}
