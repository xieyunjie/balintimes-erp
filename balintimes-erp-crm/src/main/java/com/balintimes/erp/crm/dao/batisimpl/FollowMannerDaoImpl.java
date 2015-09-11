package com.balintimes.erp.crm.dao.batisimpl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.balintimes.erp.crm.dao.FollowMannerDao;
import com.balintimes.erp.crm.mappers.FollowMannerMapper;
import com.balintimes.erp.crm.model.FollowManner;

@Repository
public class FollowMannerDaoImpl implements FollowMannerDao {

	@Resource
	private FollowMannerMapper followMannerMapper;

	public List<FollowManner> getMannerList() {
		// TODO Auto-generated method stub
		return this.followMannerMapper.getMannerList();
	}

	public void createManner(FollowManner followManner) {
		// TODO Auto-generated method stub
		this.followMannerMapper.createManner(followManner);
	}

}
