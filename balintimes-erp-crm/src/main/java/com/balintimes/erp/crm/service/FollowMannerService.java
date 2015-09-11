package com.balintimes.erp.crm.service;

import java.util.List;

import com.balintimes.erp.crm.model.FollowManner;

public interface FollowMannerService {
	List<FollowManner> getMannerList();

	void createManner(FollowManner followManner);
}
