package com.balintimes.erp.crm.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.balintimes.erp.crm.dao.CustomerDao;
import com.balintimes.erp.crm.model.Customer;
import com.balintimes.erp.crm.service.CustomerService;
import com.balintimes.erp.util.tuples.TuplePage;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Resource
	private CustomerDao customerDao;

	public TuplePage<List<Customer>, Integer> getCustomerList(String name,
			int currPage, int pageSize) {
		// TODO Auto-generated method stub
		int startIndex = (currPage - 1) * pageSize;
		List<Customer> list = this.customerDao.getCustomerList(name,
				startIndex, pageSize);
		int totalCount = this.customerDao.getCountCustomerByList(name);
		return new TuplePage<List<Customer>, Integer>(list, totalCount);
	}

	public Customer getCustomer(int uid) {
		// TODO Auto-generated method stub
		return this.customerDao.getCustomer(uid);
	}

}
