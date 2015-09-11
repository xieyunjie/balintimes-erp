package com.balintimes.erp.crm.dao;

import java.util.List;

import com.balintimes.erp.crm.model.FollowManner;

public interface FollowMannerDao {
	List<FollowManner> getMannerList();

	void createManner(FollowManner followManner);
}
