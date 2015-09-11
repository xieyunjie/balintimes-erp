package com.balintimes.erp.center.mappers;

import com.balintimes.erp.center.model.UserRoles;

import java.util.List;
import java.util.Map;

public interface UserRolesMapper {
	List<UserRoles> GetUserRolesListByUser(String userUid);
	
	void CreateUserRoles(UserRoles userRoles);
	
	void DeleteUserRoleByUserAndRole(Map<String, Object> map);
	
	void DeleteUserRoleByUser(String userUid);
	
	void DeleteUserRoleByRole(String roleUid);
}
