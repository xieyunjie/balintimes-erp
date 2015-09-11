package com.balintimes.erp.center.dao.impl.base;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.balintimes.erp.center.mappers.base.CustomerCategoryMapper;
import com.balintimes.erp.center.model.base.CustomerCategory;
import com.balintimes.erp.center.dao.base.CustomerCategoryDao;

@Repository
public class CustomerCategoryDaoImpl implements CustomerCategoryDao {

	@Resource
	private CustomerCategoryMapper customerCategoryMapper;

	public List<CustomerCategory> GetCustomerCategoryList(String name) {
		// TODO Auto-generated method stub
		if (name == null)
			name = "";
		name = "%" + name + "%";
		return this.customerCategoryMapper.GetCustomerCategoryList(name);
	}

	public CustomerCategory GetCustomerCategory(String uid) {
		// TODO Auto-generated method stub
		return this.customerCategoryMapper.GetCustomerCategory(uid);
	}

	public void CreateCustomerCategory(CustomerCategory customerCategory) {
		// TODO Auto-generated method stub
		this.customerCategoryMapper.CreateCustomerCategory(customerCategory);
	}

	public void UpdateCustomerCategory(CustomerCategory customerCategory) {
		// TODO Auto-generated method stub
		this.customerCategoryMapper.UpdateCustomerCategory(customerCategory);
	}

	public void DeleteCustomerCategory(String uid) {
		// TODO Auto-generated method stub
		this.customerCategoryMapper.DeleteCustomerCategory(uid);
	}

}
