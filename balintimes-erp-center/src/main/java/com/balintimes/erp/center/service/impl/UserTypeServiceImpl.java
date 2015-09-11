package com.balintimes.erp.center.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.balintimes.erp.center.dao.UserTypeDao;
import com.balintimes.erp.center.model.UserType;
import com.balintimes.erp.center.service.UserTypeService;

@Service("userTypeService")
public class UserTypeServiceImpl implements UserTypeService
{
	@Resource
	private UserTypeDao userTypeDao;

	public List<UserType> GetUserType()
	{
		return userTypeDao.GetUserType();
	}

}
