package com.balintimes.erp.center.mappers;

import java.util.List;
import java.util.Map;

import com.balintimes.erp.center.model.RoleApplication;

public interface RoleApplicationMapper {
	List<RoleApplication> GetRoleApplicationListByRole(String roleUid);

	int GetRoleApplicationCountByRole(Map<String, Object> map);

	void CreateRoleApplication(RoleApplication roleApplication);

	void DeleteRoleApplicationByRoleAndApp(Map<String, Object> map);

	void DeleteRoleApplicationByRole(String roleUid);

	void DeleteRoleApplicationByApp(String appUid);
}
