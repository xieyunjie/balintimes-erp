package com.balintimes.erp.center.dao.base;

import java.util.List;

import com.balintimes.erp.center.model.base.BusinessType;

public interface BusinessTypeDao {
	List<BusinessType> GetBusinessTypeList(String name);
	
	BusinessType GetBusinessType(String uid);
	
	void CreateBusinessType(BusinessType businessType);
	
	void UpdateBusinessType(BusinessType businessType);
	
	void DeleteBusinessType(String uid);
}
