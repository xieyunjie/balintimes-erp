package com.balintimes.erp.center.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.balintimes.erp.center.dao.RoleTypeDao;
import com.balintimes.erp.center.model.RoleType;
import com.balintimes.erp.center.service.RoleTypeService;

@Service
public class RoleTypeServiceImpl implements RoleTypeService {

	@Resource
	private RoleTypeDao roleTypeDao;

	public List<RoleType> GetRoleTypeList() {
		// TODO Auto-generated method stub
		return this.roleTypeDao.GetRoleTypeList();
	}

}
