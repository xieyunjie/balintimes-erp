package com.balintimes.erp.center.dao.impl.base;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.balintimes.erp.center.mappers.base.BusinessTypeMapper;
import com.balintimes.erp.center.model.base.BusinessType;
import com.balintimes.erp.center.dao.base.BusinessTypeDao;

@Repository
public class BusinessTypeDaoImpl implements BusinessTypeDao {

	@Resource
	private BusinessTypeMapper businessTypeMapper;

	public List<BusinessType> GetBusinessTypeList(String name) {
		// TODO Auto-generated method stub
		if (name == null)
			name = "";
		name = "%" + name + "%";
		return this.businessTypeMapper.GetBusinessTypeList(name);
	}

	public BusinessType GetBusinessType(String uid) {
		// TODO Auto-generated method stub
		return this.businessTypeMapper.GetBusinessType(uid);
	}

	public void CreateBusinessType(BusinessType businessType) {
		// TODO Auto-generated method stub
		this.businessTypeMapper.CreateBusinessType(businessType);
	}

	public void UpdateBusinessType(BusinessType businessType) {
		// TODO Auto-generated method stub
		this.businessTypeMapper.UpdateBusinessType(businessType);
	}

	public void DeleteBusinessType(String uid) {
		// TODO Auto-generated method stub
		this.businessTypeMapper.DeleteBusinessType(uid);
	}

}
