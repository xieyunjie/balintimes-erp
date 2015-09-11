package com.balintimes.erp.crm.mappers;

import java.util.List;

import com.balintimes.erp.crm.model.FollowManner;

public interface FollowMannerMapper {
	List<FollowManner> getMannerList();

	void createManner(FollowManner followManner);
}
