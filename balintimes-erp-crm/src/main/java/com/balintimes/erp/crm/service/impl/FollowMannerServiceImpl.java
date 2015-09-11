package com.balintimes.erp.crm.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.balintimes.erp.crm.dao.FollowMannerDao;
import com.balintimes.erp.crm.model.FollowManner;
import com.balintimes.erp.crm.service.FollowMannerService;

@Service
public class FollowMannerServiceImpl implements FollowMannerService {

	@Resource
	private FollowMannerDao followMannerDao;

	public List<FollowManner> getMannerList() {
		// TODO Auto-generated method stub
		return this.followMannerDao.getMannerList();
	}

	public void createManner(FollowManner followManner) {
		// TODO Auto-generated method stub
		this.followMannerDao.createManner(followManner);
	}

}
