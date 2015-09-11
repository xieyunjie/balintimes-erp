package com.balintimes.erp.crm.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.balintimes.erp.crm.dao.CustomerProcDao;
import com.balintimes.erp.crm.model.EmpCustomer;
import com.balintimes.erp.crm.service.CustomerProcService;
import com.balintimes.erp.util.tuples.TuplePage;

@Service
public class CustomerProcServiceImpl implements CustomerProcService {

	@Resource
	private CustomerProcDao customerProcDao;
	
	public TuplePage<List<EmpCustomer>, Integer> getCustomerByEmp(String name,
			String userUids, String businessTypeUid, int isReg, String brand,
			int pageSize, int currPage) {
		// TODO Auto-generated method stub
		return this.customerProcDao.getCustomerByEmp(name, userUids, businessTypeUid, isReg, brand, pageSize, currPage);
	}

}
