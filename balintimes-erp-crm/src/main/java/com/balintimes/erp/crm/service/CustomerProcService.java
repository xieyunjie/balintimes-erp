package com.balintimes.erp.crm.service;

import java.util.List;

import com.balintimes.erp.crm.model.EmpCustomer;
import com.balintimes.erp.util.tuples.TuplePage;

public interface CustomerProcService {
	TuplePage<List<EmpCustomer>, Integer> getCustomerByEmp(String name,
			String userUids, String businessTypeUid, int isReg, String brand,
			int pageSize, int currPage);
}
