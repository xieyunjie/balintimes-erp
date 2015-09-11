package com.balintimes.erp.center.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.balintimes.erp.center.mappers.UserTypeMapper;
import com.balintimes.erp.center.model.UserType;
import com.balintimes.erp.center.dao.UserTypeDao;

@Repository("userTypeDao")
public class UserTypeDaoImpl implements UserTypeDao
{

	@Resource
	private UserTypeMapper userTypeMapper;

	public List<UserType> GetUserType()
	{
		return userTypeMapper.GetUserType();
	}

}
