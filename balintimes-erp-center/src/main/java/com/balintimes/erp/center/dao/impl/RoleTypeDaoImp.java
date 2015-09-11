package com.balintimes.erp.center.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.balintimes.erp.center.mappers.RoleTypeMapper;
import com.balintimes.erp.center.model.RoleType;
import com.balintimes.erp.center.dao.RoleTypeDao;

@Repository
public class RoleTypeDaoImp implements RoleTypeDao {

	@Resource
	private RoleTypeMapper roleTypeMapper;

	public List<RoleType> GetRoleTypeList() {
		// TODO Auto-generated method stub
		return this.roleTypeMapper.GetRoleTypeList();
	}

}
