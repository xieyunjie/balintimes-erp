package com.balintimes.erp.crm.mappers;

import java.util.List;
import java.util.Map;

import com.balintimes.erp.crm.model.EmpCustomer;

public interface CustomerProcMapper {
	List<EmpCustomer> getCustomerByEmp(Map<String, Object> map);
}
