package com.balintimes.erp.crm.dao;

import java.util.List;

import com.balintimes.erp.util.tuples.TuplePage;
import com.balintimes.erp.crm.model.EmpCustomer;

public interface CustomerProcDao {
	TuplePage<List<EmpCustomer>, Integer> getCustomerByEmp(String name,
			String userUids, String businessTypeUid, int isReg, String brand,
			int pageSize, int currPage);
}