package com.balintimes.erp.crm.dao;

import java.util.Date;
import java.util.List;

import com.balintimes.erp.util.tuples.TuplePage;
import com.balintimes.erp.crm.model.EmpCustomer;
import com.balintimes.erp.crm.model.RemarksInfo;

public interface CustomerProcDao {
	TuplePage<List<EmpCustomer>, Integer> getCustomerByEmp(String name,
			String userUids, String businessTypeUid, int isReg, String brand,
			int pageSize, int currPage);

	TuplePage<List<RemarksInfo>, Integer> getRemarksByEmp(String customerName,
			String brand, String userUids, Integer mannerUid, Date beginDate,
			Date endDate, int pageSize, int currPage);
}