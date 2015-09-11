package com.balintimes.erp.crm.dao.batisimpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.balintimes.erp.crm.dao.CustomerDao;
import com.balintimes.erp.crm.mappers.CustomerMapper;
import com.balintimes.erp.crm.model.Customer;

@Repository
public class CustomerDaoImpl implements CustomerDao {

	@Resource
	private CustomerMapper customerMapper;

	public List<Customer> getCustomerList(String name, int startIndex,
			int pageSize) {
		// TODO Auto-generated method stub
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("name", name);
		map.put("startIndex", startIndex);
		map.put("pageSize", pageSize);
		return this.customerMapper.getCustomerList(map);
	}

	public int getCountCustomerByList(String name) {
		// TODO Auto-generated method stub
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("name", name);
		return this.customerMapper.getCountCustomerByList(map);
	}

	public Customer getCustomer(int uid) {
		// TODO Auto-generated method stub
		return this.customerMapper.getCustomer(uid);
	}

}
