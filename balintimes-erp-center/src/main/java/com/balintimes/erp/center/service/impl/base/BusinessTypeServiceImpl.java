package com.balintimes.erp.center.service.impl.base;

import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.balintimes.erp.center.annotation.CustomerTransactional;
import com.balintimes.erp.center.dao.base.BusinessTypeDao;
import com.balintimes.erp.center.model.base.BusinessType;
import com.balintimes.erp.center.service.base.BusinessTypeService;

@Service
public class BusinessTypeServiceImpl implements BusinessTypeService {

	@Resource
	private BusinessTypeDao businessTypeDao;

	public List<BusinessType> GetBusinessTypeList(String name) {
		// TODO Auto-generated method stub
		return this.businessTypeDao.GetBusinessTypeList(name);
	}

	public BusinessType GetBusinessType(String uid) {
		// TODO Auto-generated method stub
		return this.businessTypeDao.GetBusinessType(uid);
	}

	@CustomerTransactional
	public void CreateBusinessType(BusinessType businessType) {
		// TODO Auto-generated method stub
		businessType.setUid(UUID.randomUUID().toString());
		this.businessTypeDao.CreateBusinessType(businessType);
	}

	@CustomerTransactional
	public void UpdateBusinessType(BusinessType businessType) {
		// TODO Auto-generated method stub
		this.businessTypeDao.UpdateBusinessType(businessType);
	}

	@CustomerTransactional
	public void DeleteBusinessType(String uid) {
		// TODO Auto-generated method stub
		this.businessTypeDao.DeleteBusinessType(uid);
	}

}
