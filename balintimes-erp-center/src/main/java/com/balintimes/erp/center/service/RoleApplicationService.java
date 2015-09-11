package com.balintimes.erp.center.service;

import java.util.List;

import com.balintimes.erp.center.model.RoleApplication;

public interface RoleApplicationService {
	List<RoleApplication> GetRoleApplicationListByRole(String roleUid);
	
	void SaveRoleApplicationSetting(String roleUid, String appUid, boolean checked);
}
